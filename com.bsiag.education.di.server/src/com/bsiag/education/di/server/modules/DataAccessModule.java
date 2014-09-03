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
  private static final String DB_LOCATION =
      "D:/education/DI/workspaces/step01/git/educationDi/com.bsiag.education.di.database/db";
  private static final String SEQUENCE_NAME = "SEQ";
  private static final String SEQUENCE_COLUMN_NAME = "NEXT_VAL";

  @Override
  public void configure(Binder binder) {
    binder.bind(String.class).annotatedWith(Names.named("DB-LOCATION")).toInstance(DB_LOCATION);
    binder.bind(String.class).annotatedWith(Names.named("SEQUENCE_COLUMN_NAME")).toInstance(SEQUENCE_COLUMN_NAME);
    binder.bind(String.class).annotatedWith(Names.named("SEQUENCE_NAME")).toInstance(SEQUENCE_NAME);

    binder.bind(ISqlService.class).to(DerbySqlService.class);

  }

}
