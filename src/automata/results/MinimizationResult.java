package automata.results;

import java.util.ArrayList;

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

        public Step(Pair<State> states, EquivalenceResult table, Automata m) {
            this.states = states;
            this.table = table;
            automataRepr = m.toString();
        }

        @Override
        public String toString() {
            StringBuilder repr = new StringBuilder();

            repr.append("Analizamos " + states.first().getName()
                    + " Y " + states.second().getName() + "\n\n");

            repr.append(table).append("\n");

            repr.append("Se elimina " + states.second().getName() + "\n\n");

            repr.append(automataRepr).append("\n");

            return repr.toString();
        }
    }
}
