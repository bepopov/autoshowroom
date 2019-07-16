package ru.merann.bopopov.autoshowroom.groovy_server.model

import org.springframework.data.cassandra.core.mapping.Table

@Table("makes")
class Make {

    Long id

    String name

    @Override
    boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Make make = (Make) o;
        return Objects.equals(this.id, make.id) &&
                Objects.equals(this.name, make.name);
    }

    @Override
    int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Make {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
