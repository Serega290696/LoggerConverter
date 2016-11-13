package org.beltser;

import ch.qos.logback.classic.PatternLayout;

public class PatternLayoutWithUserContext  extends PatternLayout {
  static {
    PatternLayout.defaultConverterMap.put(
        "custom", CustomConverter.class.getName());
  }
}
