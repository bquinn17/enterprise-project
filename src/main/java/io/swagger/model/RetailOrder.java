package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RetailOrder
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-03T19:46:44.474Z")

@Entity
@Table(name = "retail_orders")
public class RetailOrder   {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "customer_email")
  @JsonProperty("customerEmail")
  private String customerEmail = null;

  @Column(name = "customer_street_address")
  @JsonProperty("customerShippingStreetAddress")
  private String customerShippingStreetAddress = null;

  @Column(name = "customer_zip")
  @JsonProperty("customerShippingZip")
  private String customerShippingZip = null;

  @Column(name = "customer_town")
  @JsonProperty("customerShippingTown")
  private String customerShippingTown = null;

  @Column(name = "customer_state")
  @JsonProperty("customerShippingState")
  private String customerShippingState = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    FULLFILLED("fullfilled"),
    
    SHIPPED("shipped"),
    
    ARRIVED("arrived");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @Column(name = "order_status")
  @JsonProperty("status")
  private StatusEnum status = null;

  @Transient
  @JsonProperty("products")
  private List<Product> products = null;

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

  public RetailOrder status(StatusEnum status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")


  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public RetailOrder products(List<Product> products) {
    this.products = products;
    return this;
  }

  public RetailOrder addProductsItem(Product productsItem) {
    if (this.products == null) {
      this.products = new ArrayList<Product>();
    }
    this.products.add(productsItem);
    return this;
  }

   /**
   * Get products
   * @return products
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
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
        Objects.equals(this.customerShippingState, retailOrder.customerShippingState) &&
        Objects.equals(this.status, retailOrder.status) &&
        Objects.equals(this.products, retailOrder.products);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerEmail, customerShippingStreetAddress, customerShippingZip, customerShippingTown, customerShippingState, status, products);
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
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    products: ").append(toIndentedString(products)).append("\n");
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

