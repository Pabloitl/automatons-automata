package automata;

import java.util.HashMap;
import java.util.Set;

public class State {
    private String name;

    private final HashMap<Character, State> transitions;

    boolean isFinal = false;

    public State(String name) {
        transitions = new HashMap<>();
        this.name = name;
    }

    public void addTransition(Character symbol, State nextState) {
        transitions.put(symbol, nextState);
    }

    public State transition(Character symbol) {
        return transitions.getOrDefault(symbol, this);
    }

    public void setFinal() {
        isFinal = true;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public String getName() {
        return name;
    }

    public Set<Character> getAlphabet() {
        return transitions.keySet();
    }

    @Override
    public String toString() {
        StringBuilder repr = new StringBuilder("State → " + name + "\n");

        for (Character key : transitions.keySet()) {
            repr.append(key + " → " + transitions.get(key).name + "\n");
        }

        return repr.toString();
    }
}
