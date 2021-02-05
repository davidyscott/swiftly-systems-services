package com.useswiftly.dscott.model.input;

import java.util.function.BiConsumer;

/**
 * Definition of all known/understood Flags in the Flags field of the input file format.
 */
public enum FlagDefinition {
  PER_WEIGHT(3, Flags::setByWeight),
  TAXABLE(5, Flags::setTaxable);

  public static final char FLAG_ENABLED = 'Y';
  public static final char FLAG_DISABLED = 'N';

  private final int cardinal;
  private final BiConsumer<Flags, Boolean> propertySetter;

  FlagDefinition(
      int cardinal,
      BiConsumer<Flags, Boolean> propertySetter
  ) {
    this.cardinal = cardinal;
    this.propertySetter = propertySetter;
  }

  public int getCardinal() {
    return cardinal;
  }

  public BiConsumer<Flags, Boolean> getPropertySetter() {
    return propertySetter;
  }
}
