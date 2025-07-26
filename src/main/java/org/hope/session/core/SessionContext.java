package org.hope.session.core;

public class SessionContext {

  private SessionContext() {}

  private static final ThreadLocal<Session> threadLocalSession = new InheritableThreadLocal<>();

  public static void set(Session session) {
    threadLocalSession.set(session);
  }

  public static Session get() {
    return threadLocalSession.get();
  }

  public static void clear() {
    threadLocalSession.remove();
  }
}
