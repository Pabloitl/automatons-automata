package main;

import automata.Automata;
import automata.AutomataBuilder;
import automata.operations.Equivalence;

public class Main {
    public static void main(String[] args) {
        Automata automata = new AutomataBuilder()
            .addState("a")
            .addState("b")
            .addTransition("a", 'a', "b")
            .addTransition("b", 'a', "a")
            .setInitialState("a")
            .setFinalState("a")
            .get();

        Automata automata2 = new AutomataBuilder()
            .addState("a")
            .addTransition("a", 'a', "a")
            .setInitialState("a")
            .setFinalState("a")
            .get();

        System.out.println(Equivalence.test(automata, automata2));

    }
}
