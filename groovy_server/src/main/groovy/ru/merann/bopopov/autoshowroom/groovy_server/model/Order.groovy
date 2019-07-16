package ru.merann.bopopov.autoshowroom.groovy_server.model

import com.datastax.driver.core.DataType
import org.springframework.data.cassandra.core.mapping.CassandraType
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table("orders")
class Order {

    @PrimaryKey
    Long id

    @CassandraType(type = DataType.Name.UDT, userTypeName = "car")
    Car car

    String client

    Status status

    @Override
    boolean equals(Object o) {
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
    int hashCode() {
        return Objects.hash(id, client, car, status);
    }

    @Override
    String toString() {
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
