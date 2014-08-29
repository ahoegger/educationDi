/**
 *
 */
package com.bsiag.education.di.server.services.data;

import org.eclipse.scout.rt.services.common.jdbc.AbstractDerbySqlService;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

/**
 * @author aho
 */
@Singleton
public class DerbySqlService extends AbstractDerbySqlService {

  private final String m_dbLocation;

  @Inject
  private DerbySqlService(@Named("DB-LOCATION") String dbLocation) {
    m_dbLocation = dbLocation;

    // TODO Auto-generated constructor stub
  }

  @Override
  public String getJdbcMappingName() {
    System.out.println(m_dbLocation);
    return "jdbc:derby:" + m_dbLocation;//D:\\education\\DI\\scoutExample\\workspace\\com.bsiag.education.di.database\\db";
  }
}
