package main;

import automata.Automata;
import automata.AutomataBuilder;
import automata.operations.Equivalence;

public class Main {
    public static void main(String[] args) {
        Automata automata = new AutomataBuilder()
            .addState("1")
            .addState("2")
            .addState("3")
            .addState("4")
            .addTransition("1", 'a', "1")
            .addTransition("1", 'b', "2")
            .addTransition("1", 'c', "1")
            .addTransition("2", 'a', "4")
            .addTransition("2", 'b', "4")
            .addTransition("2", 'c', "3")
            .addTransition("3", 'a', "3")
            .addTransition("3", 'b', "2")
            .addTransition("3", 'c', "3")
            .addTransition("4", 'a', "4")
            .addTransition("4", 'b', "4")
            .addTransition("4", 'c', "4")
            .setInitialState("1")
            .setFinalState("1")
            .setFinalState("3")
            .get();

        Automata automata2 = new AutomataBuilder()
            .addState("1")
            .addState("2")
            .addState("3")
            .addTransition("1", 'a', "1")
            .addTransition("1", 'b', "2")
            .addTransition("1", 'c', "1")
            .addTransition("2", 'a', "3")
            .addTransition("2", 'b', "3")
            .addTransition("2", 'c', "1")
            .addTransition("3", 'a', "3")
            .addTransition("3", 'b', "3")
            .addTransition("3", 'c', "3")
            .setInitialState("1")
            .setFinalState("1")
            .get();

        System.out.println(Equivalence.test(automata, automata2));

    }
}
