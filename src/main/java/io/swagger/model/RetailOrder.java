package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RetailOrder
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-01T23:17:54.274Z")

public class RetailOrder   {
  @JsonProperty("customerEmail")
  private String customerEmail = null;

  @JsonProperty("customerShippingStreetAddress")
  private String customerShippingStreetAddress = null;

  @JsonProperty("customerShippingZip")
  private String customerShippingZip = null;

  @JsonProperty("customerShippingTown")
  private String customerShippingTown = null;

  @JsonProperty("customerShippingState")
  private String customerShippingState = null;

  public RetailOrder customerEmail(String customerEmail) {
    this.customerEmail = customerEmail;
    return this;
  }

   /**
   * Get customerEmail
   * @return customerEmail
  **/
  @ApiModelProperty(value = "")


  public String getCustomerEmail() {
    return customerEmail;
  }

  public void setCustomerEmail(String customerEmail) {
    this.customerEmail = customerEmail;
  }

  public RetailOrder customerShippingStreetAddress(String customerShippingStreetAddress) {
    this.customerShippingStreetAddress = customerShippingStreetAddress;
    return this;
  }

   /**
   * Get customerShippingStreetAddress
   * @return customerShippingStreetAddress
  **/
  @ApiModelProperty(value = "")


  public String getCustomerShippingStreetAddress() {
    return customerShippingStreetAddress;
  }

  public void setCustomerShippingStreetAddress(String customerShippingStreetAddress) {
    this.customerShippingStreetAddress = customerShippingStreetAddress;
  }

  public RetailOrder customerShippingZip(String customerShippingZip) {
    this.customerShippingZip = customerShippingZip;
    return this;
  }

   /**
   * Get customerShippingZip
   * @return customerShippingZip
  **/
  @ApiModelProperty(value = "")


  public String getCustomerShippingZip() {
    return customerShippingZip;
  }

  public void setCustomerShippingZip(String customerShippingZip) {
    this.customerShippingZip = customerShippingZip;
  }

  public RetailOrder customerShippingTown(String customerShippingTown) {
    this.customerShippingTown = customerShippingTown;
    return this;
  }

   /**
   * Get customerShippingTown
   * @return customerShippingTown
  **/
  @ApiModelProperty(value = "")


  public String getCustomerShippingTown() {
    return customerShippingTown;
  }

  public void setCustomerShippingTown(String customerShippingTown) {
    this.customerShippingTown = customerShippingTown;
  }

  public RetailOrder customerShippingState(String customerShippingState) {
    this.customerShippingState = customerShippingState;
    return this;
  }

   /**
   * Get customerShippingState
   * @return customerShippingState
  **/
  @ApiModelProperty(value = "")


  public String getCustomerShippingState() {
    return customerShippingState;
  }

  public void setCustomerShippingState(String customerShippingState) {
    this.customerShippingState = customerShippingState;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RetailOrder retailOrder = (RetailOrder) o;
    return Objects.equals(this.customerEmail, retailOrder.customerEmail) &&
        Objects.equals(this.customerShippingStreetAddress, retailOrder.customerShippingStreetAddress) &&
        Objects.equals(this.customerShippingZip, retailOrder.customerShippingZip) &&
        Objects.equals(this.customerShippingTown, retailOrder.customerShippingTown) &&
        Objects.equals(this.customerShippingState, retailOrder.customerShippingState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerEmail, customerShippingStreetAddress, customerShippingZip, customerShippingTown, customerShippingState);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RetailOrder {\n");
    
    sb.append("    customerEmail: ").append(toIndentedString(customerEmail)).append("\n");
    sb.append("    customerShippingStreetAddress: ").append(toIndentedString(customerShippingStreetAddress)).append("\n");
    sb.append("    customerShippingZip: ").append(toIndentedString(customerShippingZip)).append("\n");
    sb.append("    customerShippingTown: ").append(toIndentedString(customerShippingTown)).append("\n");
    sb.append("    customerShippingState: ").append(toIndentedString(customerShippingState)).append("\n");
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

