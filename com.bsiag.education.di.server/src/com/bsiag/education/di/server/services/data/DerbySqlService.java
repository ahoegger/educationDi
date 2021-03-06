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
  }

  @Override
  public String getJdbcMappingName() {
    return "jdbc:derby:" + m_dbLocation;
  }

}
