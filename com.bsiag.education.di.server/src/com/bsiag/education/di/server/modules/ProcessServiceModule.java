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
import com.bsiag.education.di.server.services.process.DesktopService;
import com.bsiag.education.di.server.services.process.PersonProcessService;
import com.bsiag.education.di.shared.services.IDesktopService;
import com.bsiag.education.di.shared.services.IPersonProcessService;
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

    binder.bind(IDesktopService.class).to(DesktopService.class);
    binder.bind(IPersonProcessService.class).to(PersonProcessService.class);

  }
}
