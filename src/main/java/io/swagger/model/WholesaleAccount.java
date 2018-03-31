package io.swagger.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/** TODO Add configured prices into DB. Seperate table with foreign key.
 * WholesaleAccount
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-30T18:00:05.067Z")
@Entity
@Table(name = "wholesale_account")
public class WholesaleAccount   {

  @Id
  @JsonIgnore
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("shippingAddress")
  private String shippingAddress = null;

  @JsonProperty("shippingState")
  private String shippingState = null;

  @JsonProperty("shippingTown")
  private String shippingTown = null;

  @JsonProperty("shippingZip")
  private String shippingZip = null;

  public Long getId(){
    return this.id;
  }

  public WholesaleAccount email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(value = "")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public WholesaleAccount shippingAddress(String shippingAddress) {
    this.shippingAddress = shippingAddress;
    return this;
  }

  /**
   * Get shippingAddress
   * @return shippingAddress
  **/
  @ApiModelProperty(value = "")


  public String getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(String shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public WholesaleAccount shippingState(String shippingState) {
    this.shippingState = shippingState;
    return this;
  }

  /**
   * Get shippingState
   * @return shippingState
  **/
  @ApiModelProperty(value = "")


  public String getShippingState() {
    return shippingState;
  }

  public void setShippingState(String shippingState) {
    this.shippingState = shippingState;
  }

  public WholesaleAccount shippingTown(String shippingTown) {
    this.shippingTown = shippingTown;
    return this;
  }

  /**
   * Get shippingTown
   * @return shippingTown
  **/
  @ApiModelProperty(value = "")


  public String getShippingTown() {
    return shippingTown;
  }

  public void setShippingTown(String shippingTown) {
    this.shippingTown = shippingTown;
  }

  public WholesaleAccount shippingZip(String shippingZip) {
    this.shippingZip = shippingZip;
    return this;
  }

  /**
   * Get shippingZip
   * @return shippingZip
  **/
  @ApiModelProperty(value = "")


  public String getShippingZip() {
    return shippingZip;
  }

  public void setShippingZip(String shippingZip) {
    this.shippingZip = shippingZip;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WholesaleAccount wholesaleAccount = (WholesaleAccount) o;
    return Objects.equals(this.email, wholesaleAccount.email) &&
        Objects.equals(this.shippingAddress, wholesaleAccount.shippingAddress) &&
        Objects.equals(this.shippingState, wholesaleAccount.shippingState) &&
        Objects.equals(this.shippingTown, wholesaleAccount.shippingTown) &&
        Objects.equals(this.shippingZip, wholesaleAccount.shippingZip);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, shippingAddress, shippingState, shippingTown, shippingZip);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WholesaleAccount {\n");
    
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    shippingAddress: ").append(toIndentedString(shippingAddress)).append("\n");
    sb.append("    shippingState: ").append(toIndentedString(shippingState)).append("\n");
    sb.append("    shippingTown: ").append(toIndentedString(shippingTown)).append("\n");
    sb.append("    shippingZip: ").append(toIndentedString(shippingZip)).append("\n");
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

