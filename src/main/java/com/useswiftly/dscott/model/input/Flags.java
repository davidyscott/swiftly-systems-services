package com.useswiftly.dscott.model.input;

/**
 * Extremely thin model class encapsulating the Flags field of an input row in a more
 * usable format.
 */
public class Flags {
  private boolean byWeight;
  private boolean taxable;

  public boolean isByWeight() {
    return byWeight;
  }

  public void setByWeight(boolean byWeight) {
    this.byWeight = byWeight;
  }

  public boolean isTaxable() {
    return taxable;
  }

  public void setTaxable(boolean taxable) {
    this.taxable = taxable;
  }
}

