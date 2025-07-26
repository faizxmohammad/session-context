package org.hope.session.core;

import java.util.Map;

public interface Session {
  String getId();
  Object getAttribute(String key);
  void setAttribute(String key, Object value);
  void removeAttribute(String key);
  Map<String, Object> getAllAttributes();
  long getCreationTime();
  long getLastAccessTime();

}
