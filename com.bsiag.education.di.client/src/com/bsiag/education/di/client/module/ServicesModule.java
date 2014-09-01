/**
 *
 */
package com.bsiag.education.di.client.module;

import org.eclipse.scout.rt.client.ClientSyncJob;
import org.eclipse.scout.rt.servicetunnel.ServiceTunnelUtility;

import com.bsiag.education.di.shared.services.IDesktopProcessService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * @author aho
 */
public class ServicesModule extends AbstractModule {

  @Override
  protected void configure() {
  }

  @Provides
  @Singleton
  private IDesktopProcessService provideDesktopService() {
    IDesktopProcessService service = ServiceTunnelUtility.createProxy(IDesktopProcessService.class, ClientSyncJob.getCurrentSession().getServiceTunnel());
    return service;
  }

}
