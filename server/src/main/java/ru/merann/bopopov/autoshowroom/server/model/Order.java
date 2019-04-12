package ru.merann.bopopov.autoshowroom.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ru.merann.bopopov.autoshowroom.server.model.Car;
import ru.merann.bopopov.autoshowroom.server.model.Client;
import ru.merann.bopopov.autoshowroom.server.model.Status;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Model containing order information
 */
@ApiModel(description = "Model containing order information")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-04-10T04:54:51.691+03:00[Europe/Moscow]")
@Entity
@Table(name = "orders")
public class Order   {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonProperty("client")
  @ManyToOne
  @JoinColumn(name = "client_id")
  private Client client = null;

  @JsonProperty("car")
  @Embedded
  private Car car = null;

  @JsonProperty("status")
  @Enumerated(EnumType.STRING)
  private Status status = null;

  public Order id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(example = "4", value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Order client(Client client) {
    this.client = client;
    return this;
  }

  /**
   * Get client
   * @return client
  */
  @ApiModelProperty(value = "")


  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
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

  public Order status(Status status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @ApiModelProperty(value = "")


  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }


  @Override
  public boolean equals(Object o) {
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
        Objects.equals(this.status, order.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, client, car, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Order {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    client: ").append(toIndentedString(client)).append("\n");
    sb.append("    car: ").append(toIndentedString(car)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

