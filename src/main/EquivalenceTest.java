package main;

import automata.Automata;
import automata.operations.Equivalence;
import io.AutomataReader;

public class EquivalenceTest {
    public static void main(String[] args) {
        Automata automata1 = null, automata2 = null;

        try {
            automata1 = AutomataReader.fromFile("resources/automata1.txt");
            automata2 = AutomataReader.fromFile("resources/automata2.txt");
        } catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println(Equivalence.test(automata1, automata2));
    }
}
