package ru.merann.bopopov.autoshowroom.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import ru.merann.bopopov.autoshowroom.server.model.Option;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ResultListOption
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-04-22T11:54:55.246+03:00[Europe/Moscow]")

public class ResultListOption   {
  @JsonProperty("items")
  @Valid
  private List<Option> items = null;

  public ResultListOption items(List<Option> items) {
    this.items = items;
    return this;
  }

  public ResultListOption addItemsItem(Option itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<>();
    }
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Get items
   * @return items
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<Option> getItems() {
    return items;
  }

  public void setItems(List<Option> items) {
    this.items = items;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResultListOption resultListOption = (ResultListOption) o;
    return Objects.equals(this.items, resultListOption.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(items);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResultListOption {\n");
    
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
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

