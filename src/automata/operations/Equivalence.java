package automata.operations;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import utils.Pair;

import automata.Automata;
import automata.State;

public class Equivalence {
    public static boolean test(Automata m1, Automata m2) {
        if (!compareAlphabets(m1.getAlphabet(), m2.getAlphabet()))
            return false;

        return verifyWithTable(m1, m2);
    }

    private static boolean compareAlphabets(Set<Character> a1, Set<Character> a2) {
        return a1.containsAll(a2) && a2.containsAll(a1);
    }

    private static boolean verifyWithTable(Automata m1, Automata m2) {
        Set<String> seen = new HashSet<>();
        Queue<Pair<State>> pairs = new LinkedList<>();

        Pair<State> initials = new Pair<>(
                m1.getInitialState(),
                m2.getInitialState()
                );
        if (!compatible(initials)) return false;
        pairs.add(initials);
        seen.add(initials.toString());

        while (!pairs.isEmpty()) {
            Pair<State> current = pairs.poll();

            for (Character symbol : m1.getAlphabet()) {
                Pair<State> next = new Pair<>(
                        current.first().transition(symbol),
                        current.second().transition(symbol)
                        );

                if (!compatible(next)) return false;

                if (seen.contains(next.toString())) continue;

                seen.add(next.toString());
                pairs.add(next);
            }
        }

        return true;
    }

    private static boolean compatible(Pair<State> pair) {
        return pair.first().isFinal() == pair.second().isFinal();
    }
}
