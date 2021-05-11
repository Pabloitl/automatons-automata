package utils;

public class Pair <T> {
    T first, second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T first() {
        return first;
    }

    public T second() {
        return second;
    }

    @Override
    public String toString() {
        return first + "\n" + second;
    }
}
