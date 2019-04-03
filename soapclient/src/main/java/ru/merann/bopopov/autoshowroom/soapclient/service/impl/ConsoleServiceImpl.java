package ru.merann.bopopov.autoshowroom.soapclient.service.impl;

import org.springframework.stereotype.Service;
import ru.merann.bopopov.autoshowroom.soapclient.service.ConsoleService;

import java.io.PrintStream;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    private final static String ANSI_YELLOW = "\u001B[33m";
    private final static String ANSI_RESET = "\u001B[0m";

    private final PrintStream out = System.out;

    @Override
    public void write(String message, String... args) {
        this.out.print("> ");
        this.out.print(ANSI_YELLOW);
        this.out.printf(message, (Object []) args);
        this.out.println(ANSI_RESET);
    }
}
