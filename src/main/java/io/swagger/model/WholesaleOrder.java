package io.swagger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.Valid;

/**
 * WholesaleOrder
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-03T19:46:44.474Z")

@Entity
@Table(name = "wholesale_order")
public class WholesaleOrder
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date")
  private Date dateCreated = new Date();

  @ManyToOne
    private WholesaleAccount wholesaleAccount = null;

  @JsonProperty("salesRep")
  @ManyToOne
  private SalesRep salesRep = null;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private StatusEnum status = null;

  @JsonProperty("orderMap")
  @OneToMany(mappedBy="order_id")
  private List<ModelCount> orderMap = null;

  @JsonProperty("totalPrice")
  private double totalPrice = 0.0;

  public Long getId() {
    return id;
  }

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


  public WholesaleOrder wholesaleAccount(WholesaleAccount wholesaleAccount) {
    this.wholesaleAccount = wholesaleAccount;
    return this;
  }

  public Date getDateCreated(){
    return dateCreated;
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

  public WholesaleOrder salesRep(SalesRep salesRep) {
    this.salesRep = salesRep;
    return this;
  }

  /**
   * Get salesRep
   * @return salesRep
  **/
  @ApiModelProperty(value = "")

  @Valid

  public SalesRep getSalesRep() {
    return salesRep;
  }

  public void setSalesRep(SalesRep salesRep) {
    this.salesRep = salesRep;
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

  public WholesaleOrder orderMap(List<ModelCount> orderMap) {
    this.orderMap = orderMap;
    return this;
  }

  public WholesaleOrder addOrderMapItem(ModelCount orderMapItem) {
    if (this.orderMap == null) {
      this.orderMap = new ArrayList<ModelCount>();
    }
    this.orderMap.add(orderMapItem);
    return this;
  }

  /**
   * Get orderMap
   * @return orderMap
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<ModelCount> getOrderMap() {
    return orderMap;
  }

  public void setOrderMap(List<ModelCount> orderMap) {
    this.orderMap = orderMap;
  }

  public WholesaleOrder totalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }

  /**
   * Get totalPrice
   * @return totalPrice
  **/
  @ApiModelProperty(value = "")

  @Valid

  public double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
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
        Objects.equals(this.salesRep, wholesaleOrder.salesRep) &&
        Objects.equals(this.status, wholesaleOrder.status) &&
        Objects.equals(this.orderMap, wholesaleOrder.orderMap) &&
        Objects.equals(this.totalPrice, wholesaleOrder.totalPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(wholesaleAccount, salesRep, status, orderMap, totalPrice);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WholesaleOrder {\n");

    sb.append("    wholesaleAccount: ").append(toIndentedString(wholesaleAccount)).append("\n");
    sb.append("    salesRep: ").append(toIndentedString(salesRep)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    orderMap: ").append(toIndentedString(orderMap)).append("\n");
    sb.append("    totalPrice: ").append(toIndentedString(totalPrice)).append("\n");
    sb.append("    dateCreated: ").append(toIndentedString(dateCreated)).append("\n");
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

