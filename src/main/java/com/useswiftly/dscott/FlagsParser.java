package com.useswiftly.dscott;

import com.useswiftly.dscott.model.input.FlagDefinition;
import com.useswiftly.dscott.model.input.Flags;

/**
 * Single-use factory class for turning the raw flags field into a more developer-friendly Flags
 * object.
 */
public class FlagsParser {
  private final String rawFlags;

  public FlagsParser(String rawFlags) {
    this.rawFlags = rawFlags;
  }

  /**
   * This implementation is completely agnostic to the meaning of flags as it relies on the
   * definitions in {@link FlagDefinition}.
   * @return
   */
  public Flags parse() {
    final Flags flags = new Flags();

    for (var flagDef : FlagDefinition.values()) {
      // convert one-indexed to zero-indexed
      char rawValue = rawFlags.charAt(flagDef.getCardinal() - 1);
      boolean value;
      switch (rawValue) {
        case FlagDefinition.FLAG_ENABLED:
          value = true;
          break;
        case FlagDefinition.FLAG_DISABLED:
          value = false;
          break;
        default:
          throw new IllegalArgumentException("Invalid flag character: " + rawValue);
      }

      flagDef.getPropertySetter().accept(flags, value);
    }

    return flags;
  }
}
