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
 * Model containing order information
 */
@ApiModel(description = "Model containing order information")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-04-09T12:54:49.820+03:00[Europe/Moscow]")

public class Order   {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("client")
  private String client;

  @JsonProperty("car")
  private Car car = null;

  @JsonProperty("status")
  private String status;

  @JsonProperty("options")
  @Valid
  private List<Option> options = null;

  public Order id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(example = "4", value = "")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Order client(String client) {
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

  public Order car(Car car) {
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

  public Order status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @ApiModelProperty(example = "ACCEPTED", value = "")


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Order options(List<Option> options) {
    this.options = options;
    return this;
  }

  public Order addOptionsItem(Option optionsItem) {
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
    Order order = (Order) o;
    return Objects.equals(this.id, order.id) &&
        Objects.equals(this.client, order.client) &&
        Objects.equals(this.car, order.car) &&
        Objects.equals(this.status, order.status) &&
        Objects.equals(this.options, order.options);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, client, car, status, options);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Order {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    client: ").append(toIndentedString(client)).append("\n");
    sb.append("    car: ").append(toIndentedString(car)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

