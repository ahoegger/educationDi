/**
 *
 */
package com.bsiag.education.di.server.ex;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Set;

import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.commons.serialization.SerializationUtility;
import org.eclipse.scout.rt.server.DefaultTransactionDelegate;
import org.eclipse.scout.rt.server.IServerSession;
import org.eclipse.scout.rt.server.ThreadContext;
import org.eclipse.scout.rt.server.admin.inspector.CallInspector;
import org.eclipse.scout.rt.server.admin.inspector.ProcessInspector;
import org.eclipse.scout.rt.server.admin.inspector.SessionInspector;
import org.eclipse.scout.rt.server.services.common.clientnotification.IClientNotificationService;
import org.eclipse.scout.rt.server.transaction.AbstractTransactionMember;
import org.eclipse.scout.rt.shared.services.common.clientnotification.IClientNotification;
import org.eclipse.scout.rt.shared.servicetunnel.ServiceTunnelRequest;
import org.eclipse.scout.rt.shared.servicetunnel.ServiceTunnelResponse;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.service.SERVICES;
import org.eclipse.scout.service.ServiceUtility;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

import com.bsiag.education.di.server.ServerSession;

/**
 * @author aho
 */
public class DefaultTransactionDelegateEx extends DefaultTransactionDelegate {
  private static final IScoutLogger LOG = ScoutLogManager.getLogger(DefaultTransactionDelegateEx.class);

  /**
   * @param loaderBundles
   * @param requestMinVersion
   * @param debug
   */
  public DefaultTransactionDelegateEx(Bundle[] loaderBundles, Version requestMinVersion, boolean debug) {
    super(loaderBundles, requestMinVersion, debug);
  }

  @Override
  protected ServiceTunnelResponse invokeImpl(ServiceTunnelRequest serviceReq) throws Throwable {
    String soapOperation = ServiceTunnelRequest.toSoapOperation(serviceReq.getServiceInterfaceClassName(), serviceReq.getOperation());
    IServerSession serverSession = ThreadContext.getServerSession();
    String authenticatedUser = serverSession.getUserId();
    if (LOG.isDebugEnabled()) {
      LOG.debug("started " + serviceReq.getServiceInterfaceClassName() + "." + serviceReq.getOperation() + " by " + authenticatedUser + " at " + new Date());
    }
    Set<String> consumedNotifications = serviceReq.getConsumedNotifications();
    if (consumedNotifications != null && consumedNotifications.size() > 0) {
      IClientNotificationService notificationService = SERVICES.getService(IClientNotificationService.class);
      notificationService.ackNotifications(consumedNotifications);
    }
    CallInspector callInspector = null;
    SessionInspector sessionInspector = ProcessInspector.getDefault().getSessionInspector(serverSession, true);
    if (sessionInspector != null) {
      callInspector = sessionInspector.requestCallInspector(serviceReq);
    }
    ServiceTunnelResponse serviceRes = null;
    try {
      //do checks
      Class<?> serviceInterfaceClass = SerializationUtility.getClassLoader().loadClass(serviceReq.getServiceInterfaceClassName());
      //check access: service proxy allowed
      Method serviceOp = ServiceUtility.getServiceOperation(serviceInterfaceClass, serviceReq.getOperation(), serviceReq.getParameterTypes());
      checkRemoteServiceAccessByInterface(serviceInterfaceClass, serviceOp, serviceReq.getArgs());
      //check access: service impl exists

      Object service = ServerSession.getInjector().getInstance(serviceInterfaceClass);
//      Object service = SERVICES.getService(serviceInterfaceClass);
      if (service == null) {
        throw new SecurityException("service registry does not contain a service of type " + serviceReq.getServiceInterfaceClassName());
      }
      checkRemoteServiceAccessByAnnotations(serviceInterfaceClass, service.getClass(), serviceOp, serviceReq.getArgs());
      checkRemoteServiceAccessByPermission(serviceInterfaceClass, service.getClass(), serviceOp, serviceReq.getArgs());
      //all checks done
      //
      //filter input
      if (serviceReq.getArgs() != null && serviceReq.getArgs().length > 0) {
        Class<? extends IValidationStrategy> inputValidationStrategyClass = findInputValidationStrategyByAnnotation(service, serviceOp);
        if (inputValidationStrategyClass == null) {
          inputValidationStrategyClass = findInputValidationStrategyByPolicy(service, serviceOp);
        }
        if (inputValidationStrategyClass == null) {
          throw new SecurityException("input validation failed (no strategy defined)");
        }
        validateInput(inputValidationStrategyClass.newInstance(), service, serviceOp, serviceReq.getArgs());
      }
      //
      Object data = ServiceUtility.invoke(serviceOp, service, serviceReq.getArgs());
      Object[] outParameters = ServiceUtility.extractHolderArguments(serviceReq.getArgs());
      //
      //filter output
      if (data != null || (outParameters != null && outParameters.length > 0)) {
        Class<? extends IValidationStrategy> outputValidationStrategyClass = findOutputValidationStrategyByAnnotation(service, serviceOp);
        if (outputValidationStrategyClass == null) {
          outputValidationStrategyClass = findOutputValidationStrategyByPolicy(service, serviceOp);
        }
        if (outputValidationStrategyClass == null) {
          throw new SecurityException("output validation failed");
        }
        validateOutput(outputValidationStrategyClass.newInstance(), service, serviceOp, data, outParameters);
      }
      //
      serviceRes = new ServiceTunnelResponse(data, outParameters, null);
      serviceRes.setSoapOperation(soapOperation);

      ThreadContext.getTransaction().registerMember(new P_ClientNotificationTransactionMember(serviceRes));
      return serviceRes;
    }
    finally {
      if (callInspector != null) {
        try {
          callInspector.update();
        }
        catch (Throwable t) {
          LOG.warn(null, t);
        }
        try {
          callInspector.close(serviceRes);
        }
        catch (Throwable t) {
          LOG.warn(null, t);
        }
        try {
          callInspector.getSessionInspector().update();
        }
        catch (Throwable t) {
          LOG.warn(null, t);
        }
      }
    }
  }

  /**
   * This transaction member ensures that the retrieval of client notifications is done at the last possible moment, and
   * not during the normal duration of the transaction. Notifications are added to the global notification queue at
   * commit-time, so this is in fact needed.
   */
  private static class P_ClientNotificationTransactionMember extends AbstractTransactionMember {

    private static final String TRANSACTION_MEMBER_ID = P_ClientNotificationTransactionMember.class.getSimpleName();

    private final ServiceTunnelResponse m_serviceTunnelResponse;

    public P_ClientNotificationTransactionMember(ServiceTunnelResponse serviceRes) {
      super(TRANSACTION_MEMBER_ID);
      m_serviceTunnelResponse = serviceRes;
    }

    @Override
    public boolean needsCommit() {
      return true;
    }

    @Override
    public boolean commitPhase1() {
      return true;
    }

    @Override
    public void commitPhase2() {
    }

    @Override
    public void rollback() {
    }

    @Override
    public void release() {
      Set<IClientNotification> nextNotifications = SERVICES.getService(IClientNotificationService.class).getNextNotifications(0);
      m_serviceTunnelResponse.setClientNotifications(nextNotifications);
    }

  }

}
