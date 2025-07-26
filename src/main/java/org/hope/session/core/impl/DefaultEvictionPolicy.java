package org.hope.session.core.impl;

import org.hope.session.core.EvictionPolicy;
import org.hope.session.core.Session;

public class DefaultEvictionPolicy implements EvictionPolicy {

  private final long sessionTimeoutMillis;

  public DefaultEvictionPolicy(long sessionTimeoutMillis) {
    this.sessionTimeoutMillis = sessionTimeoutMillis;
  }

  @Override
  public boolean shouldEvict(Session session) {
    return System.currentTimeMillis() - session.getLastAccessTime() > sessionTimeoutMillis;
  }
}
