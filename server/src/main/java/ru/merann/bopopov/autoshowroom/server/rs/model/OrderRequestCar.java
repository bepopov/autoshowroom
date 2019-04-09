package ru.merann.bopopov.autoshowroom.server.rs.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Contains car info
 */
@ApiModel(description = "Contains car info")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-04-09T15:03:19.360+03:00[Europe/Moscow]")

public class OrderRequestCar   {
  @JsonProperty("make")
  private String make;

  @JsonProperty("model")
  private String model;

  public OrderRequestCar make(String make) {
    this.make = make;
    return this;
  }

  /**
   * Get make
   * @return make
  */
  @ApiModelProperty(example = "Audi", value = "")


  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public OrderRequestCar model(String model) {
    this.model = model;
    return this;
  }

  /**
   * Get model
   * @return model
  */
  @ApiModelProperty(example = "A8", value = "")


  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderRequestCar orderRequestCar = (OrderRequestCar) o;
    return Objects.equals(this.make, orderRequestCar.make) &&
        Objects.equals(this.model, orderRequestCar.model);
  }

  @Override
  public int hashCode() {
    return Objects.hash(make, model);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderRequestCar {\n");
    
    sb.append("    make: ").append(toIndentedString(make)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
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

