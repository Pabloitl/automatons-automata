package automata.operations;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import utils.Pair;

import automata.Automata;
import automata.State;

public class Equivalence {
    public static EquivalenceResult test(Automata m1, Automata m2) {
        if (!compareAlphabets(m1.getAlphabet(), m2.getAlphabet()))
            return new EquivalenceResult(false);

        return verifyWithTable(m1, m2);
    }

    private static boolean compareAlphabets(Set<Character> a1, Set<Character> a2) {
        return a1.containsAll(a2) && a2.containsAll(a1);
    }

    private static EquivalenceResult verifyWithTable(Automata m1, Automata m2) {
        EquivalenceResult res = new EquivalenceResult(m1.getAlphabet());
        Set<String> seen = new HashSet<>();
        Queue<Pair<State>> pairs = new LinkedList<>();

        Pair<State> initialPair =
            new Pair<>(m1.getInitialState(), m2.getInitialState());

        if (!visitPair(seen, pairs, initialPair)) {
            return res;
        }

        while (!pairs.isEmpty()) {
            Pair<State> current = pairs.poll();

            res.addRow(current);
            for (Character symbol : m1.getAlphabet()) {
                Pair<State> next = new Pair<>(
                        current.first().transition(symbol),
                        current.second().transition(symbol)
                        );

                res.addHop(next);
                if (!visitPair(seen, pairs, next)) {
                    return res;
                }
            }
        }

        res.setEquivalent(true);
        return res;
    }

    private static boolean visitPair(Set<String> seen, Queue<Pair<State>> pairs, Pair<State> pair) {
        if (!compatible(pair)) return false;
        if (seen.contains(pair.toString())) return true;

        pairs.add(pair);
        seen.add(pair.toString());

        return true;
    }

    private static boolean compatible(Pair<State> pair) {
        return pair.first().isFinal() == pair.second().isFinal();
    }
}
