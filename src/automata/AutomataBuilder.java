package automata;

import java.util.HashMap;

public class AutomataBuilder {
    HashMap<String, State> states;

    Automata automata;

    public AutomataBuilder() {
        states = new HashMap<>();
    }

    public AutomataBuilder addState(String name) {
        State state = new State(name);

        states.put(state.getName(), state);
        return this;
    }

    public AutomataBuilder addTransition(String originState, char symbol, String nextState) {
        states.get(originState).addTransition(symbol, states.get(nextState));

        return this;
    }

    public AutomataBuilder setInitialState(String state) {
        automata = new Automata(states.get(state));

        return this;
    }

    public AutomataBuilder setFinalState(String state) {
        states.get(state).setFinal();

        return this;
    }

    public Automata get() {
        return automata;
    }
}
