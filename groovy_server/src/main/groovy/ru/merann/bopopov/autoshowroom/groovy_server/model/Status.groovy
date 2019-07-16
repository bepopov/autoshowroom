package ru.merann.bopopov.autoshowroom.groovy_server.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum Status {

    ACCEPTED("ACCEPTED"),

    INPROGRESS("INPROGRESS"),

    COMPLETE("COMPLETE");

    String value;

    Status(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    static Status fromValue(String text) {
        for (Status b : values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
}
