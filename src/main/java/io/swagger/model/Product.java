package io.swagger.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Product
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-30T18:00:05.067Z")

@Entity
@Table(name = "product")
public class Product   {

  @Column(name = "model")
  @JsonProperty("model")
  private String model = null;

  @Id
  @Column(name = "serial_number")
  private String serialNumber = null;

  @Column(name = "refurbished")
  @JsonProperty("refurbished")
  private Boolean refurbished = null;

  @JsonProperty("priceSoldAt")
  private double priceSoldAt = 0.0;

  @JsonIgnore
  @ManyToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name="retailOrderId")
  private RetailOrder retailOrder = null;

  public Product model(String model) {
    this.model = model;
    return this;
  }

  /**
   * Get model
   * @return model
  **/
  @ApiModelProperty(value = "")


  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Product serialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
    return this;
  }

  /**
   * Get serialNumber
   * @return serialNumber
  **/
  @ApiModelProperty(value = "")


  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public Product refurbished(Boolean refurbished) {
    this.refurbished = refurbished;
    return this;
  }

  /**
   * Get refurbished
   * @return refurbished
  **/
  @ApiModelProperty(value = "")


  public Boolean getRefurbished() {
    return refurbished;
  }

  public void setRefurbished(Boolean refurbished) {
    this.refurbished = refurbished;
  }

  public Product priceSoldAt(double priceSoldAt) {
    this.priceSoldAt = priceSoldAt;
    return this;
  }

  /**
   * Get priceSoldAt
   * @return priceSoldAt
  **/
  @ApiModelProperty(value = "")

  @Valid

  public double getPriceSoldAt() {
    return priceSoldAt;
  }

  public void setPriceSoldAt(double priceSoldAt) {
    this.priceSoldAt = priceSoldAt;
  }


  /**
   * Get Set retail order
   * @return retailOrder
   */

  public RetailOrder getRetailOrder() {
    return retailOrder;
  }

  public void setRetailOrder(RetailOrder r){
    this.retailOrder = r;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(this.model, product.model) &&
        Objects.equals(this.serialNumber, product.serialNumber) &&
        Objects.equals(this.refurbished, product.refurbished) &&
        Objects.equals(this.priceSoldAt, product.priceSoldAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(model, serialNumber, refurbished, priceSoldAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Product {\n");
    
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    serialNumber: ").append(toIndentedString(serialNumber)).append("\n");
    sb.append("    refurbished: ").append(toIndentedString(refurbished)).append("\n");
    sb.append("    priceSoldAt: ").append(toIndentedString(priceSoldAt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

