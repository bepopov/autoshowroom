package ru.merann.bopopov.autoshowroom.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ru.merann.bopopov.autoshowroom.server.model.Make;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Contains model information
 */
@ApiModel(description = "Contains model information")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-04-10T04:54:51.691+03:00[Europe/Moscow]")
@Entity
@Table(name = "models")
public class Model   {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("make")
  @ManyToOne
  @JoinColumn(name = "make_id")
  private Make make = null;

  @JsonProperty("price")
  private Long price;

  public Model id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(example = "3", value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Model name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(example = "A8", value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Model make(Make make) {
    this.make = make;
    return this;
  }

  /**
   * Get make
   * @return make
  */
  @ApiModelProperty(value = "")


  public Make getMake() {
    return make;
  }

  public void setMake(Make make) {
    this.make = make;
  }

  public Model price(Long price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  */
  @ApiModelProperty(example = "6000000", value = "")


  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Model model = (Model) o;
    return Objects.equals(this.id, model.id) &&
        Objects.equals(this.name, model.name) &&
        Objects.equals(this.make, model.make) &&
        Objects.equals(this.price, model.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, make, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Model {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    make: ").append(toIndentedString(make)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
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

