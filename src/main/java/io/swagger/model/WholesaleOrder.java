package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.WholesaleAccount;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * WholesaleOrder
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-03T19:46:44.474Z")

public class WholesaleOrder   {
  @JsonProperty("wholesaleAccount")
  private WholesaleAccount wholesaleAccount = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    PLACED("placed"),
    
    PAID("paid"),
    
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

  @JsonProperty("status")
  private StatusEnum status = null;

  @JsonProperty("orderMap")
  private Object orderMap = null;

  public WholesaleOrder wholesaleAccount(WholesaleAccount wholesaleAccount) {
    this.wholesaleAccount = wholesaleAccount;
    return this;
  }

   /**
   * Get wholesaleAccount
   * @return wholesaleAccount
  **/
  @ApiModelProperty(value = "")

  @Valid

  public WholesaleAccount getWholesaleAccount() {
    return wholesaleAccount;
  }

  public void setWholesaleAccount(WholesaleAccount wholesaleAccount) {
    this.wholesaleAccount = wholesaleAccount;
  }

  public WholesaleOrder status(StatusEnum status) {
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

  public WholesaleOrder orderMap(Object orderMap) {
    this.orderMap = orderMap;
    return this;
  }

   /**
   * Get orderMap
   * @return orderMap
  **/
  @ApiModelProperty(value = "")


  public Object getOrderMap() {
    return orderMap;
  }

  public void setOrderMap(Object orderMap) {
    this.orderMap = orderMap;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WholesaleOrder wholesaleOrder = (WholesaleOrder) o;
    return Objects.equals(this.wholesaleAccount, wholesaleOrder.wholesaleAccount) &&
        Objects.equals(this.status, wholesaleOrder.status) &&
        Objects.equals(this.orderMap, wholesaleOrder.orderMap);
  }

  @Override
  public int hashCode() {
    return Objects.hash(wholesaleAccount, status, orderMap);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WholesaleOrder {\n");
    
    sb.append("    wholesaleAccount: ").append(toIndentedString(wholesaleAccount)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    orderMap: ").append(toIndentedString(orderMap)).append("\n");
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
