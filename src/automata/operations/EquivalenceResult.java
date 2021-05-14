package automata.operations;

import java.util.ArrayList;
import java.util.Set;

import automata.State;
import utils.Pair;

public class EquivalenceResult {
    private boolean equivalent = false;

    private ArrayList<ArrayList<Pair<State>>> table;
    private int rowIdx = -1;

    private Set<Character> alphabet;

    public EquivalenceResult(Set<Character> alphabet) {
        table = new ArrayList<>();
        this.alphabet = alphabet;
    }

    public EquivalenceResult(boolean equivalent) {
        table = new ArrayList<>();
        this.equivalent = equivalent;
    }

    public void addRow(Pair<State> pair) {
        ArrayList<Pair<State>> row =  new ArrayList<>();

        row.add(pair);
        table.add(row);
        ++rowIdx;
    }

    public void addHop(Pair<State> pair) {
        table.get(rowIdx).add(pair);
    }

    public void setEquivalent(boolean equivalent) {
        this.equivalent = equivalent;
    }

    @Override
    public String toString() {
        StringBuilder repr = new StringBuilder();

        repr.append("|\tM1\tM2\t|");
        for (Character a : alphabet) {
            repr.append("|\t" + a + "(M1)\t" + a + "(M2)\t|");
        }
        repr.append("\n");

        for (ArrayList<Pair<State>> col : table) {
            col.stream()
                .forEach(p -> repr.append("|\t" + p.first().getName() + "\t" + p.second().getName() + "\t|"));
            repr.append("\n");
        }

        repr.append("\n");
        repr.append((equivalent)
                ? "Los automatas son equivalentes\n"
                : "Los automatas no son equivalentes\n");

        return repr.toString();
    }
}
