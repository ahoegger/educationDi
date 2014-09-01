package com.bsiag.education.di.client;

import org.eclipse.scout.commons.UriUtility;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.AbstractClientSession;
import org.eclipse.scout.rt.client.ClientJob;
import org.eclipse.scout.rt.client.ClientSyncJob;
import org.eclipse.scout.rt.client.servicetunnel.http.ClientHttpServiceTunnel;
import org.eclipse.scout.rt.shared.services.common.code.CODES;

import com.bsiag.education.di.client.module.ServicesModule;
import com.bsiag.education.di.client.module.UtilModule;
import com.bsiag.education.di.client.ui.desktop.Desktop;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class ClientSession extends AbstractClientSession {
  private static IScoutLogger logger = ScoutLogManager.getLogger(ClientSession.class);
  private Injector m_injector;

  public ClientSession() {
    super(true);
  }

  /**
   * @return session in current ThreadContext
   */
  public static ClientSession get() {
    return ClientJob.getCurrentSession(ClientSession.class);
  }

  @Override
  public void execLoadSession() throws ProcessingException {
    setServiceTunnel(new ClientHttpServiceTunnel(this, UriUtility.toUrl(getBundle().getBundleContext().getProperty("server.url"))));

    setupGuice();
    //pre-load all known code types
    CODES.getAllCodeTypes(com.bsiag.education.di.shared.Activator.PLUGIN_ID);

    setDesktop(new Desktop());

    // turn client notification polling on
    // getServiceTunnel().setClientNotificationPollInterval(2000L);
  }

  /**
   *
   */
  private void setupGuice() {
    m_injector = Guice.createInjector(
        new UtilModule(),
        new ServicesModule()
        );

  }

  private Injector getInjectorInternal() {
    return m_injector;
  }

  public static Injector getInjector() {
    return ((ClientSession) ClientSyncJob.getCurrentSession()).getInjectorInternal();
  }

  @Override
  public void execStoreSession() throws ProcessingException {
  }
}
