package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import automata.Automata;
import automata.AutomataBuilder;

public class AutomataReader {
    public static Automata fromFile(String file) throws IOException {
        AutomataBuilder builder = new AutomataBuilder();

        Files.lines(Paths.get(file))
            .forEach(line -> apply(builder, line.split("\\s")));

        return builder.get();
    }

    private static void apply(AutomataBuilder builder, String[] args) {
        switch (args[0]) {
            case "addState":
                builder.addState(args[1]);
                break;
            case "addTransition":
                builder.addTransition(args[1], args[2].charAt(0), args[3]);
                break;
            case "setInitialState":
                builder.setInitialState(args[1]);
                break;
            case "setFinalState":
                builder.setFinalState(args[1]);
                break;
        }
    }
}
