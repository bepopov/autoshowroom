package ru.merann.bopopov.autoshowroom.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import ru.merann.bopopov.autoshowroom.server.model.Make;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ResultListMake
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-04-22T11:54:55.246+03:00[Europe/Moscow]")

public class ResultListMake   {
  @JsonProperty("items")
  @Valid
  private List<Make> items = null;

  public ResultListMake items(List<Make> items) {
    this.items = items;
    return this;
  }

  public ResultListMake addItemsItem(Make itemsItem) {
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

  public List<Make> getItems() {
    return items;
  }

  public void setItems(List<Make> items) {
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
    ResultListMake resultListMake = (ResultListMake) o;
    return Objects.equals(this.items, resultListMake.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(items);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResultListMake {\n");
    
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

