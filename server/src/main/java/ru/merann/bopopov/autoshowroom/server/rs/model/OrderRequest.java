package ru.merann.bopopov.autoshowroom.server.rs.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import ru.merann.bopopov.autoshowroom.server.rs.model.Car;
import ru.merann.bopopov.autoshowroom.server.rs.model.Option;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Model used for creating and updating order
 */
@ApiModel(description = "Model used for creating and updating order")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-04-09T12:54:49.820+03:00[Europe/Moscow]")

public class OrderRequest   {
  @JsonProperty("client")
  private String client;

  @JsonProperty("car")
  private Car car = null;

  @JsonProperty("options")
  @Valid
  private List<Option> options = null;

  public OrderRequest client(String client) {
    this.client = client;
    return this;
  }

  /**
   * Username
   * @return client
  */
  @ApiModelProperty(example = "Bogdan", value = "Username")


  public String getClient() {
    return client;
  }

  public void setClient(String client) {
    this.client = client;
  }

  public OrderRequest car(Car car) {
    this.car = car;
    return this;
  }

  /**
   * Get car
   * @return car
  */
  @ApiModelProperty(value = "")


  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public OrderRequest options(List<Option> options) {
    this.options = options;
    return this;
  }

  public OrderRequest addOptionsItem(Option optionsItem) {
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

  @Valid

  public List<Option> getOptions() {
    return options;
  }

  public void setOptions(List<Option> options) {
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
    return Objects.equals(this.client, orderRequest.client) &&
        Objects.equals(this.car, orderRequest.car) &&
        Objects.equals(this.options, orderRequest.options);
  }

  @Override
  public int hashCode() {
    return Objects.hash(client, car, options);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderRequest {\n");
    
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

