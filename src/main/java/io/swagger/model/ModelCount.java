package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A key value pair of a model to quantity
 */
@ApiModel(description = "A key value pair of a model to quantity")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-03T19:46:44.474Z")

public class ModelCount   {
  @JsonProperty("model")
  private String model = null;

  @JsonProperty("quantity")
  private Integer quantity = null;

  public ModelCount model(String model) {
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

  public ModelCount quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

   /**
   * Get quantity
   * @return quantity
  **/
  @ApiModelProperty(value = "")


  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelCount modelCount = (ModelCount) o;
    return Objects.equals(this.model, modelCount.model) &&
        Objects.equals(this.quantity, modelCount.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(model, quantity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelCount {\n");
    
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
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

