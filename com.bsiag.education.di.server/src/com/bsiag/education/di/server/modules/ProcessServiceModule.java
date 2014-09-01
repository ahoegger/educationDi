/**
 *
 */
package com.bsiag.education.di.server.modules;

import org.eclipse.scout.rt.server.services.common.code.CodeService;
import org.eclipse.scout.rt.server.services.common.security.LogoutService;
import org.eclipse.scout.rt.shared.services.common.code.ICodeService;
import org.eclipse.scout.rt.shared.services.common.security.IAccessControlService;
import org.eclipse.scout.rt.shared.services.common.security.ILogoutService;

import com.bsiag.education.di.server.services.common.security.AccessControlService;
import com.bsiag.education.di.server.services.process.DesktopProcessService;
import com.bsiag.education.di.shared.services.IDesktopProcessService;
import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * @author aho
 */
public class ProcessServiceModule implements Module {

  @Override
  public void configure(Binder binder) {
    binder.bind(ICodeService.class).to(CodeService.class);
    binder.bind(IAccessControlService.class).to(AccessControlService.class);
    binder.bind(ILogoutService.class).to(LogoutService.class);

    binder.bind(IDesktopProcessService.class).to(DesktopProcessService.class);

  }
}
