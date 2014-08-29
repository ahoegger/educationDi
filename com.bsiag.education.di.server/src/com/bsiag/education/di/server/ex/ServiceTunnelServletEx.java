/**
 *
 */
package com.bsiag.education.di.server.ex;

import org.eclipse.scout.rt.server.ServiceTunnelServlet;
import org.eclipse.scout.rt.shared.servicetunnel.ServiceTunnelRequest;
import org.eclipse.scout.rt.shared.servicetunnel.ServiceTunnelResponse;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

/**
 * @author aho
 */
public class ServiceTunnelServletEx extends ServiceTunnelServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected ServiceTunnelResponse runServerJobTransactionWithDelegate(ServiceTunnelRequest req, Bundle[] loaderBundles, Version requestMinVersion, boolean debug) throws Exception {
    return new DefaultTransactionDelegateEx(loaderBundles, requestMinVersion, debug).invoke(req);
  }

}
