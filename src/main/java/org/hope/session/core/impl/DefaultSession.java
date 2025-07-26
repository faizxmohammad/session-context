package org.hope.session.core.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.hope.session.core.Session;

public class DefaultSession implements Session {
  private final String id = UUID.randomUUID().toString();
  private final Map<String, Object> attributes = new ConcurrentHashMap<>();
  private final long creationTime = System.currentTimeMillis();
  private volatile long lastAccessTime = creationTime;

  @Override
  public Object getAttribute(String key) {
    lastAccessTime = System.currentTimeMillis();
    return attributes.get(key);
  }

  @Override
  public void setAttribute(String key, Object value) {
    lastAccessTime = System.currentTimeMillis();
    attributes.put(key, value);
  }

  @Override
  public void removeAttribute(String key) {
    attributes.remove(key);
  }

  @Override
  public Map<String, Object> getAllAttributes() {
    return new HashMap<>(attributes);
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public long getCreationTime() {
    return creationTime;
  }

  @Override
  public long getLastAccessTime() {
    return lastAccessTime;
  }
}
