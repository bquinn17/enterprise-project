package io.swagger.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CollectionType;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SalesRep
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-30T18:00:05.067Z")

@Entity
@Table(name = "sales_rep")
public class SalesRep   {
  @JsonProperty("firstName")
  @Column(name = "first_name")
  private String firstName = null;

  @JsonProperty("lastName")
  @Column(name = "last_name")
  private String lastName = null;

  @Id
  @Column(name = "employee_id")
  private Long employeeId;

  public Long getEmployeeId(){
    return this.employeeId;
  }

  public void setEmployeeId(Long id){
    this.employeeId = id;
  }

  /**
   * Gets or Sets region
   */
  public enum RegionEnum {
    ROCHESTER("Rochester"),

    NORTHEAST("North East");

    private String value;

    RegionEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RegionEnum fromValue(String text) {
      for (RegionEnum b : RegionEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("region")
  @Column(name = "region")
  @Enumerated(EnumType.STRING)
  private RegionEnum region = null;

  public SalesRep firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
  **/
  @ApiModelProperty(value = "")


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public SalesRep lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
  **/
  @ApiModelProperty(value = "")


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public SalesRep region(RegionEnum region) {
    this.region = region;
    return this;
  }

  /**
   * Get region
   * @return region
  **/
  @ApiModelProperty(value = "")


  public RegionEnum getRegion() {
    return region;
  }

  public void setRegion(RegionEnum region) {
    this.region = region;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SalesRep salesRep = (SalesRep) o;
    return Objects.equals(this.firstName, salesRep.firstName) &&
        Objects.equals(this.lastName, salesRep.lastName) &&
        Objects.equals(this.region, salesRep.region);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, region);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SalesRep {\n");
    
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    region: ").append(toIndentedString(region)).append("\n");
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

