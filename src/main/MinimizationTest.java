package main;

import automata.Automata;
import automata.operations.Minimization;
import io.AutomataReader;

public class MinimizationTest {
    public static void main(String[] args) {
        Automata m = null;

        try {
            m = AutomataReader.fromFile("resources/automata1.txt");
        } catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println(Minimization.minimize(m));
    }
}
