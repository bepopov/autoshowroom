package ru.merann.bopopov.autoshowroom.soapclient.config;

import java.util.regex.Pattern;

public class CommandPatterns {

    private static final Pattern orderRequest = Pattern.compile("([A-Za-z]*\\((?<make>[1-9]+[0-9]*)\\)) " +
            "[A-Za-z0-9]*(\\((?<model>[1-9]+[0-9]*)\\))?" +
            "( (?<options>[А-Яа-я0-9 ]+\\([1-9]+[0-9]*\\)( AND [А-Яа-я0-9 ]*\\([1-9]+[0-9]*\\))*)?)?");

    private static final Pattern orderRequestText = Pattern.compile("(?<make>[A-Za-z]+(\\([1-9]+[0-9]*\\))?) " +
            "(?<model>[A-Za-z0-9]+(\\([1-9]+[0-9]*\\))?)?" +
            "( (?<options>[А-Яа-я0-9 ]+(\\([1-9]+[0-9]*\\))?( AND [А-Яа-я0-9 ]*(\\([1-9]+[0-9]*\\))?)*)?)?");

    private static final Pattern idPattern = Pattern.compile("\\((?<id>[1-9]+[0-9]*)\\)");

    private static final Pattern optionsPattern = Pattern.compile("(?<options>[А-Яа-я0-9 ]+\\([1-9]+[0-9]*\\)( AND [А-Яа-я0-9 ]*\\([1-9]+[0-9]*\\))*)");

    public static Pattern getOrderRequestPattern() {
        return orderRequest;
    }

    public static Pattern getOrderRequestTextPattern() {
        return orderRequestText;
    }

    public static Pattern getIdPattern() {
        return idPattern;
    }

    public static Pattern getOptionsPattern() {
        return optionsPattern;
    }
}
