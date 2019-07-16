package ru.merann.bopopov.autoshowroom.groovy_server.model

import org.springframework.data.cassandra.core.mapping.Table

@Table("options")
class Option {

    Long id

    String name

    Long price

    @Override
    boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Option option = (Option) o;
        return Objects.equals(this.id, option.id) &&
                Objects.equals(this.name, option.name) &&
                Objects.equals(this.price, option.price);
    }

    @Override
    int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Option {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
