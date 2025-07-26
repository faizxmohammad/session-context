package org.hope.session.core;

public interface EvictionPolicy {
  boolean shouldEvict(Session session);
}
