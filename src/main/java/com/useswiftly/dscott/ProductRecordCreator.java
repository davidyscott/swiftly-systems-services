package com.useswiftly.dscott;

import com.useswiftly.dscott.model.ProductRecord;
import com.useswiftly.dscott.model.Unit;
import com.useswiftly.dscott.model.input.FieldDefinition;
import com.useswiftly.dscott.model.input.Flags;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Map;

import static com.useswiftly.dscott.model.input.FieldDefinition.*;

/**
 * Factory class encapsulating all logic needed to produce a ProductRecord from
 * minimally-processed input.  The long-term purpose of this class is to contain complex logic that
 * may require intermediate computations and operate on multiple input fields.
 */
class ProductRecordCreator {
  // Not really sure where the best place is for this constant?  Here is okay I guess
  private static final BigDecimal TAX_RATE = new BigDecimal("0.07775");

  private final Map<FieldDefinition, String> rawFields;
  private final Flags flags;

  ProductRecordCreator(Map<FieldDefinition, String> rawFields) {
    this.rawFields = rawFields;
    this.flags = new FlagsParser(rawFields.get(FLAGS)).parse();
  }

  /**
   * Encapsulates the logic for parsing a price field from raw input.
   * Following the spec, e.g. "00001367" should be parsed as 13.67 and "-0000259" as -2.59
   */
  private static BigDecimal parsePriceField(String rawField) {
    return new BigDecimal(new BigInteger(rawField), 2);
  }

  /**
   * Encapsulates logic for generating the human-readable "display price" for a split-priced item.
   * @param splitPrice
   * @param splitQty
   * @return
   */
  private static String generateSplitDisplayPrice(BigDecimal splitPrice, int splitQty) {
    return String.format("%d for $%s", splitQty, splitPrice);
  }

  /**
   * Encapsulates logic for generating the human-readable "display price" for a singly-priced item.
   * @param price
   * @return
   */
  private static String generateSingleDisplayPrice(BigDecimal price) {
    return String.format("$%s", price);
  }

  /**
   * Encapsulates logic for dividing a split price into a unit price while following the specified
   * precision and rounding rules.
   * @param splitPrice
   * @param splitQty
   * @return
   */
  private static BigDecimal calculateEachPrice(BigDecimal splitPrice, int splitQty) {
    return splitPrice.divide(new BigDecimal(splitQty), 4, RoundingMode.HALF_DOWN);
  }

  /**
   * The basic philosophy of this implementation is that this method is the only method that
   * directly touches the ProductRecord under creation.
   *
   * @return
   */
  public ProductRecord create() {
    final ProductRecord underConstruction = new ProductRecord();

    underConstruction.setProductId(Integer.parseInt(rawFields.get(PRODUCT_ID)));
    underConstruction.setProductDescription(rawFields.get(PRODUCT_DESCRIPTION).trim());
    underConstruction.setProductSize(rawFields.get(PRODUCT_SIZE).trim());

    underConstruction.setTaxRate(getTaxRate());
    underConstruction.setUnit(getUnit());

    int regularSplitQty = Integer.parseInt(rawFields.get(REGULAR_SPLIT_QUANTITY));

    if (regularSplitQty > 0) {
      // Then we're dealing with split pricing
      var regularSplitPrice = parsePriceField(rawFields.get(REGULAR_SPLIT_PRICE));
      var regularCalculatorPrice = calculateEachPrice(regularSplitPrice, regularSplitQty);

      underConstruction.setRegularCalculatorPrice(regularCalculatorPrice);
      underConstruction.setRegularDisplayPrice(
          generateSplitDisplayPrice(regularSplitPrice, regularSplitQty));
    } else {
      // No split pricing
      var regularCalculatorPrice = parsePriceField(rawFields.get(REGULAR_EACH_PRICE));
      underConstruction.setRegularCalculatorPrice(regularCalculatorPrice);
      underConstruction.setRegularDisplayPrice(generateSingleDisplayPrice(regularCalculatorPrice));
    }

    int saleSplitQty = Integer.parseInt(rawFields.get(SALE_SPLIT_QUANTITY));
    if (saleSplitQty > 0) {
      // Then we're dealing with split pricing
      var saleSplitPrice = parsePriceField(rawFields.get(SALE_SPLIT_PRICE));

      if (saleSplitPrice.compareTo(BigDecimal.ZERO) != 0) {
        var saleCalculatorPrice = calculateEachPrice(saleSplitPrice, saleSplitQty);

        underConstruction.setSaleCalculatorPrice(saleCalculatorPrice);
        underConstruction.setSaleDisplayPrice(
            generateSplitDisplayPrice(saleSplitPrice, saleSplitQty));
      }
    } else {
      // No split pricing
      var saleCalculatorPrice = parsePriceField(rawFields.get(SALE_EACH_PRICE));
      if (saleCalculatorPrice.compareTo(BigDecimal.ZERO) != 0) {
        underConstruction.setSaleCalculatorPrice(saleCalculatorPrice);
        underConstruction.setSaleDisplayPrice(generateSingleDisplayPrice(saleCalculatorPrice));
      }
    }

    return underConstruction;
  }

  private BigDecimal getTaxRate() {
    if (flags.isTaxable()) {
      return TAX_RATE;
    } else {
      return BigDecimal.ZERO;
    }
  }

  private Unit getUnit() {
    if (flags.isByWeight()) {
      return Unit.POUND;
    } else {
      return Unit.EACH;
    }
  }
}
