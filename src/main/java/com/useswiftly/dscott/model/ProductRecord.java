package com.useswiftly.dscott.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Extremely thin model class encapsulating the specified output.
 */
public class ProductRecord {
  private Integer productId;
  private String productDescription;
  private String productSize;

  private BigDecimal taxRate;
  private Unit unit;

  private BigDecimal regularCalculatorPrice;
  private String regularDisplayPrice;

  private BigDecimal saleCalculatorPrice;
  private String saleDisplayPrice;

  public static ProductRecordBuilder builder() {
    return new ProductRecordBuilder();
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public String getProductDescription() {
    return productDescription;
  }

  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }

  public String getRegularDisplayPrice() {
    return regularDisplayPrice;
  }

  public void setRegularDisplayPrice(String regularDisplayPrice) {
    this.regularDisplayPrice = regularDisplayPrice;
  }

  public String getSaleDisplayPrice() {
    return saleDisplayPrice;
  }

  public void setSaleDisplayPrice(String saleDisplayPrice) {
    this.saleDisplayPrice = saleDisplayPrice;
  }

  public BigDecimal getRegularCalculatorPrice() {
    return regularCalculatorPrice;
  }

  public void setRegularCalculatorPrice(BigDecimal regularCalculatorPrice) {
    this.regularCalculatorPrice = regularCalculatorPrice;
  }

  public BigDecimal getSaleCalculatorPrice() {
    return saleCalculatorPrice;
  }

  public void setSaleCalculatorPrice(BigDecimal saleCalculatorPrice) {
    this.saleCalculatorPrice = saleCalculatorPrice;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  public String getProductSize() {
    return productSize;
  }

  public void setProductSize(String productSize) {
    this.productSize = productSize;
  }

  public BigDecimal getTaxRate() {
    return taxRate;
  }

  public void setTaxRate(BigDecimal taxRate) {
    this.taxRate = taxRate;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ProductRecord{");
    sb.append("productId=").append(productId);
    sb.append(", productDescription='").append(productDescription).append('\'');
    sb.append(", productSize='").append(productSize).append('\'');
    sb.append(", taxRate=").append(taxRate);
    sb.append(", unit=").append(unit != null ? unit.getDescription() : null);
    sb.append(", regularCalculatorPrice=").append(regularCalculatorPrice);
    sb.append(", regularDisplayPrice='").append(regularDisplayPrice).append('\'');
    sb.append(", saleCalculatorPrice=").append(saleCalculatorPrice);
    sb.append(", saleDisplayPrice='").append(saleDisplayPrice).append('\'');
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductRecord that = (ProductRecord) o;
    return Objects.equals(productId, that.productId) &&
           Objects.equals(productDescription, that.productDescription) &&
           Objects.equals(productSize, that.productSize) &&
           Objects.equals(taxRate, that.taxRate) && unit == that.unit &&
           Objects.equals(regularCalculatorPrice, that.regularCalculatorPrice) &&
           Objects.equals(regularDisplayPrice, that.regularDisplayPrice) &&
           Objects.equals(saleCalculatorPrice, that.saleCalculatorPrice) &&
           Objects.equals(saleDisplayPrice, that.saleDisplayPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId,
                        productDescription,
                        productSize,
                        taxRate,
                        unit,
                        regularCalculatorPrice,
                        regularDisplayPrice,
                        saleCalculatorPrice,
                        saleDisplayPrice);
  }

  public static final class ProductRecordBuilder {
    private Integer productId;
    private String productDescription;
    private String productSize;
    private BigDecimal taxRate;
    private Unit unit;
    private BigDecimal regularCalculatorPrice;
    private String regularDisplayPrice;
    private BigDecimal saleCalculatorPrice;
    private String saleDisplayPrice;

    public ProductRecordBuilder productId(Integer productId) {
      this.productId = productId;
      return this;
    }

    public ProductRecordBuilder productDescription(String productDescription) {
      this.productDescription = productDescription;
      return this;
    }

    public ProductRecordBuilder productSize(String productSize) {
      this.productSize = productSize;
      return this;
    }

    public ProductRecordBuilder taxRate(BigDecimal taxRate) {
      this.taxRate = taxRate;
      return this;
    }

    public ProductRecordBuilder unit(Unit unit) {
      this.unit = unit;
      return this;
    }

    public ProductRecordBuilder regularCalculatorPrice(BigDecimal regularCalculatorPrice) {
      this.regularCalculatorPrice = regularCalculatorPrice;
      return this;
    }

    public ProductRecordBuilder regularDisplayPrice(String regularDisplayPrice) {
      this.regularDisplayPrice = regularDisplayPrice;
      return this;
    }

    public ProductRecordBuilder saleCalculatorPrice(BigDecimal saleCalculatorPrice) {
      this.saleCalculatorPrice = saleCalculatorPrice;
      return this;
    }

    public ProductRecordBuilder saleDisplayPrice(String saleDisplayPrice) {
      this.saleDisplayPrice = saleDisplayPrice;
      return this;
    }

    public ProductRecord build() {
      ProductRecord productRecord = new ProductRecord();
      productRecord.setProductId(productId);
      productRecord.setProductDescription(productDescription);
      productRecord.setProductSize(productSize);
      productRecord.setTaxRate(taxRate);
      productRecord.setUnit(unit);
      productRecord.setRegularCalculatorPrice(regularCalculatorPrice);
      productRecord.setRegularDisplayPrice(regularDisplayPrice);
      productRecord.setSaleCalculatorPrice(saleCalculatorPrice);
      productRecord.setSaleDisplayPrice(saleDisplayPrice);
      return productRecord;
    }
  }
}
