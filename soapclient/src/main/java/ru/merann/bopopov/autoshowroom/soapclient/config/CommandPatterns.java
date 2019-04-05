package ru.merann.bopopov.autoshowroom.soapclient.config;

import java.util.regex.Pattern;

public class CommandPatterns {

    private static final Pattern orderSave = Pattern.compile("(?<make>[A-Za-z]+) (?<model>[A-Za-z0-9]+)?( (?<options>[А-Яа-я0-9 ]+( AND [А-Яа-я0-9 ]*)*)?)?");

    public static Pattern getOrderSavePattern() {
        return orderSave;
    }
}
