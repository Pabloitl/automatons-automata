package automata;

public class Automata {
    private State initialState;
    private State currentState;

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
}
