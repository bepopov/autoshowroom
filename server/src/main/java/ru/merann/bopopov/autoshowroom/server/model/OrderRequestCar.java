package ru.merann.bopopov.autoshowroom.server.model;

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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-04-10T11:51:56.409+03:00[Europe/Moscow]")

public class OrderRequestCar   {
  @JsonProperty("make")
  private Long make;

  @JsonProperty("model")
  private Long model;

  public OrderRequestCar make(Long make) {
    this.make = make;
    return this;
  }

  /**
   * Get make
   * @return make
  */
  @ApiModelProperty(example = "3", value = "")


  public Long getMake() {
    return make;
  }

  public void setMake(Long make) {
    this.make = make;
  }

  public OrderRequestCar model(Long model) {
    this.model = model;
    return this;
  }

  /**
   * Get model
   * @return model
  */
  @ApiModelProperty(example = "4", value = "")


  public Long getModel() {
    return model;
  }

  public void setModel(Long model) {
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

