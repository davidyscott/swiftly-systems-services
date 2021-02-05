package com.useswiftly.dscott.model.input;

/**
 * Definitions of all known/understood fields in the input file format.
 */
public enum FieldDefinition {
  PRODUCT_ID(1, 8),
  PRODUCT_DESCRIPTION(10, 68),
  REGULAR_EACH_PRICE(70, 77),
  SALE_EACH_PRICE(79, 86),
  REGULAR_SPLIT_PRICE(88, 95),
  SALE_SPLIT_PRICE(97, 104),
  REGULAR_SPLIT_QUANTITY(106, 113),
  SALE_SPLIT_QUANTITY(115, 122),
  FLAGS(124, 132),
  PRODUCT_SIZE(134, 142);

  private final int startIndex;
  private final int endIndex;

  // The indices are given in the spec as one-indexed, but our application only cares about
  // zero-indexed indices
  FieldDefinition(int startCardinal, int endCardinal) {
    this.startIndex = startCardinal - 1;
    this.endIndex = endCardinal - 1;
  }

  public int getStartIndex() {
    return startIndex;
  }

  public int getEndIndex() {
    return endIndex;
  }
}
