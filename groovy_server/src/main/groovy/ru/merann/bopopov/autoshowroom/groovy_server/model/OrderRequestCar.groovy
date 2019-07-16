package ru.merann.bopopov.autoshowroom.groovy_server.model

class OrderRequestCar {

    Long make

    Long model

    @Override
    boolean equals(Object o) {
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
    int hashCode() {
        return Objects.hash(make, model);
    }

    @Override
    String toString() {
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
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
