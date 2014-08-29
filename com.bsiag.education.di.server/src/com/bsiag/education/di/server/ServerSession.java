package com.bsiag.education.di.server;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.server.AbstractServerSession;
import org.eclipse.scout.rt.server.ServerJob;

import com.bsiag.education.di.server.modules.DataAccessModule;
import com.bsiag.education.di.server.modules.ProcessServiceModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class ServerSession extends AbstractServerSession {
  private static final long serialVersionUID = 1L;
  private static IScoutLogger logger = ScoutLogManager.getLogger(ServerSession.class);
  private Injector m_injector;

  public ServerSession() {
    super(true);
  }

  /**
   * @return session in current ThreadContext
   */
  public static ServerSession get() {
    return ServerJob.getCurrentSession(ServerSession.class);
  }

  @Override
  protected void execLoadSession() throws ProcessingException {
    logger.info("created a new session for " + getUserId());
    setupGuice();
  }

  private void setupGuice() {
    m_injector = Guice.createInjector(
        new ProcessServiceModule(),
        new DataAccessModule()
        );

  }

  private Injector getInjectorInternal() {
    return m_injector;
  }

  public static Injector getInjector() {
    return ((ServerSession) ServerJob.getCurrentSession()).getInjectorInternal();
  }

}
