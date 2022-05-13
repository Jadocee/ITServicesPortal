package com.spacejaam.itservicesportal.bean.client;

/**
 *
 */
public enum Role {
  USER("User"), IT("IT Staff");

  private final String label;

  Role(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
