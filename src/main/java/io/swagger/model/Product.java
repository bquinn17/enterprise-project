package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Product
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-03T19:46:44.474Z")

@Entity
@Table(name = "product")
public class Product   {

  @Column(name = "model")
  @JsonProperty("model")
  private String model = null;

  @Id
  @Column(name = "serial_number")
  @JsonProperty("serialNumber")
  private String serialNumber = null;

  @Column(name = "refurbished")
  @JsonProperty("refurbished")
  private Boolean refurbished = null;

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
        Objects.equals(this.refurbished, product.refurbished);
  }

  @Override
  public int hashCode() {
    return Objects.hash(model, serialNumber, refurbished);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Product {\n");
    
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    serialNumber: ").append(toIndentedString(serialNumber)).append("\n");
    sb.append("    refurbished: ").append(toIndentedString(refurbished)).append("\n");
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

