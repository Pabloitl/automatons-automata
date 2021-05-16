package automata.operations;

import java.util.HashSet;
import java.util.Set;

import automata.Automata;
import automata.State;
import automata.results.EquivalenceResult;
import automata.results.MinimizationResult;
import utils.Pair;

public class Minimization {
    public static MinimizationResult minimize(Automata m) {
        MinimizationResult res = new MinimizationResult();
        Set<String> seen = new HashSet<>();
        Set<State> states = m.getStates();

        while (seen.size() < states.size()) {

            combinations:
            for (State state1 : states) {
                for (State state2 : states) {
                    Pair<State> statesPair = new Pair<>(state1, state2);

                    if (state1 == state2 || seen.contains(statesPair.toString()))
                        continue;

                    seen.add(statesPair.toString());

                    Automata m1 = new Automata(state1);
                    Automata m2 = new Automata(state2);

                    EquivalenceResult compatible = Equivalence.test(m1, m2);
                    if (!compatible.areEquivalent())
                        continue;

                    m.replaceState(state2, state1);
                    m.removeUnreacheableStates();
                    res.addStep(statesPair, compatible, m);

                    states = m.getStates();
                    seen.clear();
                    break combinations;
                }
            }
        }


        return res;
    }
}
