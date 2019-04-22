package ru.merann.bopopov.autoshowroom.restclient.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.merann.bopopov.autoshowroom.restclient.service.ConsoleService;

import java.io.PrintStream;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    private final static String ANSI_YELLOW = "\u001B[33m";
    private final static String ANSI_RESET = "\u001B[0m";
    private static final Logger LOGGER = LogManager.getLogger(ConsoleService.class);

    private final PrintStream out = System.out;

    @Override
    public void write(String message, String... args) {
        this.out.print("> ");
        this.out.print(ANSI_YELLOW);
        this.out.printf(message, (Object []) args);
        this.out.println(ANSI_RESET);
    }
}
