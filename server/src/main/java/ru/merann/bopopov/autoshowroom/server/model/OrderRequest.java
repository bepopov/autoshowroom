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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-04-10T05:38:07.705+03:00[Europe/Moscow]")

public class OrderRequest   {
  @JsonProperty("order")
  private Long order;

  @JsonProperty("client")
  private Long client;

  @JsonProperty("car")
  private OrderRequestCar car = null;

  @JsonProperty("options")
  @Valid
  private List<Long> options = null;

  public OrderRequest order(Long order) {
    this.order = order;
    return this;
  }

  /**
   * Order id
   * @return order
  */
  @ApiModelProperty(example = "2", value = "Order id")


  public Long getOrder() {
    return order;
  }

  public void setOrder(Long order) {
    this.order = order;
  }

  public OrderRequest client(Long client) {
    this.client = client;
    return this;
  }

  /**
   * Client id
   * @return client
  */
  @ApiModelProperty(example = "2", value = "Client id")


  public Long getClient() {
    return client;
  }

  public void setClient(Long client) {
    this.client = client;
  }

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
    return Objects.equals(this.order, orderRequest.order) &&
        Objects.equals(this.client, orderRequest.client) &&
        Objects.equals(this.car, orderRequest.car) &&
        Objects.equals(this.options, orderRequest.options);
  }

  @Override
  public int hashCode() {
    return Objects.hash(order, client, car, options);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderRequest {\n");
    
    sb.append("    order: ").append(toIndentedString(order)).append("\n");
    sb.append("    client: ").append(toIndentedString(client)).append("\n");
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

