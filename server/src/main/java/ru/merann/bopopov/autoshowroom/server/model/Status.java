package ru.merann.bopopov.autoshowroom.server.model;

public enum Status {

    ACCEPTED("Принят"),
    INPROGRESS("Обрабатывается"),
    COMPLETED("Выполнен");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
