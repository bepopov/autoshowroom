package ru.merann.bopopov.autoshowroom.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import ru.merann.bopopov.autoshowroom.server.model.OrderRequestCar;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Model used for creating and updating order
 */
@ApiModel(description = "Model used for creating and updating order")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-04-10T11:51:56.409+03:00[Europe/Moscow]")

public class OrderRequest   {
  @JsonProperty("car")
  private OrderRequestCar car = null;

  @JsonProperty("options")
  @Valid
  private List<Long> options = null;

  public OrderRequest car(OrderRequestCar car) {
    this.car = car;
    return this;
  }

  /**
   * Get car
   * @return car
  */
  @ApiModelProperty(value = "")

  @Valid

  public OrderRequestCar getCar() {
    return car;
  }

  public void setCar(OrderRequestCar car) {
    this.car = car;
  }

  public OrderRequest options(List<Long> options) {
    this.options = options;
    return this;
  }

  public OrderRequest addOptionsItem(Long optionsItem) {
    if (this.options == null) {
      this.options = new ArrayList<>();
    }
    this.options.add(optionsItem);
    return this;
  }

  /**
   * Get options
   * @return options
  */
  @ApiModelProperty(value = "")


  public List<Long> getOptions() {
    return options;
  }

  public void setOptions(List<Long> options) {
    this.options = options;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderRequest orderRequest = (OrderRequest) o;
    return Objects.equals(this.car, orderRequest.car) &&
        Objects.equals(this.options, orderRequest.options);
  }

  @Override
  public int hashCode() {
    return Objects.hash(car, options);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderRequest {\n");
    
    sb.append("    car: ").append(toIndentedString(car)).append("\n");
    sb.append("    options: ").append(toIndentedString(options)).append("\n");
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

