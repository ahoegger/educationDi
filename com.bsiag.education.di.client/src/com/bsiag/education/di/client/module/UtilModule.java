/**
 *
 */
package com.bsiag.education.di.client.module;

import com.bsiag.education.di.client.services.ConsoleLogService;
import com.bsiag.education.di.client.services.ILogService;
import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * @author aho
 */
public class UtilModule implements Module {

  /* (non-Javadoc)
   * @see com.google.inject.Module#configure(com.google.inject.Binder)
   */
  @Override
  public void configure(Binder binder) {
    binder.bind(ILogService.class).to(ConsoleLogService.class);
  }
}
