package ru.merann.bopopov.autoshowroom.groovy_server.model

class OrderRequest {

    OrderRequestCar car

    List<Long> options

    @Override
    boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderRequest orderRequest = (OrderRequest) o;
        return Objects.equals(this.car, orderRequest.car) &&
                Objects.equals(this.options, orderRequest.options);
    }

    @Override
    int hashCode() {
        return Objects.hash(car, options);
    }

    @Override
    String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderRequest {\n");

        sb.append("    car: ").append(toIndentedString(car)).append("\n");
        sb.append("    options: ").append(toIndentedString(options)).append("\n");
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
