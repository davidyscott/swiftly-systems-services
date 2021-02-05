package com.useswiftly.dscott.model;

public enum Unit {
  EACH("Each"),
  POUND("Pound");

  private final String description;

  Unit(String description) {

    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
