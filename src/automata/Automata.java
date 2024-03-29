package automata;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Automata {
    private State initialState;
    private State currentState;

    private Set<State> states;

    public Automata(State initialState, Set<State> states) {
        this(initialState);

        setStates(states);
    }

    public Automata(State initialState) {
        this.initialState = initialState;

        initialize();
    }

    public void initialize() {
        currentState = initialState;
    }

    public void read(char symbol) {
        currentState = currentState.transition(symbol);
    }

    public boolean isAccepted() {
        return currentState.isFinal();
    }

    public State getInitialState() {
        return initialState;
    }

    public Set<Character> getAlphabet() {
        return initialState.getAlphabet();
    }

    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }

    public void replaceState(State stateToReplace, State replacement) {
        states.remove(stateToReplace);

        if (stateToReplace == initialState)
            initialState = replacement;

        for (State state : states) {
            for (char a : state.getAlphabet()) {
                if (state.transition(a) != stateToReplace)
                    continue;

                state.addTransition(a, replacement);
            }
        }
    }

    public Set<State> removeUnreacheableStates() {
        Set<State> reachableStates = getReachableStates();
        Set<State> removedStates;

        states.removeAll(reachableStates);
        removedStates = states;

        setStates(reachableStates);

        return removedStates;
    }

    private Set<State> getReachableStates() {
        Set<State> seen = new HashSet<>();
        Queue<State> statesQueue = new LinkedList<>();

        statesQueue.add(initialState);
        seen.add(initialState);

        while (!statesQueue.isEmpty()) {
            State state = statesQueue.poll();

            for (char a : state.getAlphabet()) {
                State next = state.transition(a);

                if (seen.contains(next))
                    continue;

                seen.add(next);
                statesQueue.add(next);
            }
        }

        return seen;
    }

    @Override
    public String toString() {
        StringBuilder repr = new StringBuilder();

        for (State state : states) {
            if (state == initialState) repr.append("(inicial)\n");
            if (state.isFinal()) repr.append("(final)\n");

            repr.append(state);
        }

        return repr.toString();
    }
}
