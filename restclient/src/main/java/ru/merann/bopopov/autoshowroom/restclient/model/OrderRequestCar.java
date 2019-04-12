/*
 * Autoshowroom API
 * An API that allows clients to obtain existing information of orders, create, edit and delete orders
 *
 * OpenAPI spec version: 1.0.0
 * Contact: ibogdanpopov@gmail.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package ru.merann.bopopov.autoshowroom.restclient.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * Contains car info
 */
@ApiModel(description = "Contains car info")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-04-12T17:46:38.506+03:00[Europe/Moscow]")
public class OrderRequestCar {
  public static final String SERIALIZED_NAME_MAKE = "make";
  @SerializedName(SERIALIZED_NAME_MAKE)
  private Long make;

  public static final String SERIALIZED_NAME_MODEL = "model";
  @SerializedName(SERIALIZED_NAME_MODEL)
  private Long model;

  public OrderRequestCar make(Long make) {
    this.make = make;
    return this;
  }

   /**
   * Get make
   * @return make
  **/
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
  **/
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

