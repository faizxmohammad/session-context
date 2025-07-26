package org.hope.session.core;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import org.hope.session.core.impl.DefaultEvictionPolicy;

public class SessionManager {
  private final EvictionPolicy evictionPolicy;
  private final Supplier<Session> sessionSupplier;
  private final Map<String, Session> activeSessions = new ConcurrentHashMap<>();


  public SessionManager(Supplier<Session> sessionSupplier) {
    this.sessionSupplier = sessionSupplier;
    this.evictionPolicy = new DefaultEvictionPolicy(1600000); // 10 minutes
  }

  public SessionManager(Supplier<Session> sessionSupplier, EvictionPolicy evictionPolicy) {
    this.sessionSupplier = sessionSupplier;
    this.evictionPolicy = evictionPolicy;
  }

  public Session createSession() {
    Session session = sessionSupplier.get();
    activeSessions.put(session.getId(), session);
    SessionContext.set(session);
    return session;
  }

  public void clearExpiredSessions() {
    activeSessions.values().removeIf(evictionPolicy::shouldEvict);
  }

  public void clearSession(String sessionId) {
    activeSessions.remove(sessionId);
  }

  public void clearCurrentSession() {
    Session session = SessionContext.get();
    if (session != null) {
      activeSessions.remove(session.getId());
    }
    SessionContext.clear();
  }

  public Optional<Session> getSessionById(String id) {
    return Optional.ofNullable(activeSessions.get(id));
  }
}

