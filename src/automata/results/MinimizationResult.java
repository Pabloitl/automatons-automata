package automata.results;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import automata.Automata;
import automata.State;
import utils.Pair;

public class MinimizationResult {
    ArrayList<Step> steps;

    public MinimizationResult() {
        steps = new ArrayList<>();
    }

    public void addStep(Pair<State> states, EquivalenceResult table, Automata m) {
        steps.add(new Step(states, table, m));
    }

    public void addStep(Pair<State> states, EquivalenceResult table, Automata m, Set<State> unreachableStates) {
        steps.add(new Step(states, table, m, unreachableStates));
    }

    @Override
    public String toString() {
        StringBuilder repr = new StringBuilder();

        steps.stream()
            .forEach(repr::append);

        return repr.toString();
    }

    private class Step {
        protected Pair<State> states;
        protected EquivalenceResult table;
        protected String automataRepr;

        protected Set<State> unreachableStates;

        public Step(Pair<State> states, EquivalenceResult table, Automata m) {
            this.states = states;
            this.table = table;
            automataRepr = m.toString();
        }

        public Step(Pair<State> states, EquivalenceResult table, Automata m, Set<State> unreachableStates) {
            this(states, table, m);
            this.unreachableStates = unreachableStates;
        }

        @Override
        public String toString() {
            StringBuilder repr = new StringBuilder();

            repr.append("Analizamos " + states.first().getName()
                    + " Y " + states.second().getName() + "\n\n");

            repr.append(table).append("\n");

            repr.append("Se elimina " + states.second().getName() + "\n\n");

            repr.append(automataRepr).append("\n");

            if (unreachableStates != null && !unreachableStates.isEmpty())
                repr.append("Se eliminaron estados inalcanzables"
                        + unreachableStates.stream()
                        .map(State::getName)
                        .collect(Collectors.joining(", ")))
                    .append("\n");

            return repr.toString();
        }
    }
}
