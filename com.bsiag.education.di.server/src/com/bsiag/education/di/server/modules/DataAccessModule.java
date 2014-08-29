/**
 *
 */
package com.bsiag.education.di.server.modules;

import org.eclipse.scout.rt.server.services.common.jdbc.ISqlService;

import com.bsiag.education.di.server.services.data.DerbySqlService;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

/**
 * @author aho
 */
public class DataAccessModule implements Module {

  private static final String DB_LOCATION = "D:/education/DI/workspaces/scoutExample/com.bsiag.education.di.database/db";

  @Override
  public void configure(Binder binder) {
    binder.bind(String.class).annotatedWith(Names.named("DB-LOCATION")).toInstance(DB_LOCATION);
    binder.bind(ISqlService.class).to(DerbySqlService.class);

  }

}
