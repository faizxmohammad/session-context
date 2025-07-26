package org.hope.session;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hope.session.core.Session;
import org.hope.session.core.SessionManager;
import org.hope.session.core.impl.DefaultEvictionPolicy;
import org.hope.session.core.impl.DefaultSession;
import org.junit.jupiter.api.Test;

class SessionManagerTest {

  @Test
  void testSessionTimeoutEviction() throws InterruptedException {
    SessionManager manager = new SessionManager(DefaultSession::new,
        new DefaultEvictionPolicy(1)); // 1 ms
    Session session = manager.createSession();
    Thread.sleep(10);
    manager.clearExpiredSessions();
    assertTrue(manager.getSessionById(session.getId()).isEmpty());
  }



}
