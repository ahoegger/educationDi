/**
 *
 */
package com.bsiag.education.di.server.modules;

import com.bsiag.education.di.server.services.PersonService;
import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * @author aho
 */
public class ServiceModule implements Module {

  /* (non-Javadoc)
   * @see com.google.inject.Module#configure(com.google.inject.Binder)
   */
  @Override
  public void configure(Binder binder) {
    binder.bind(PersonService.class);
  }

}
