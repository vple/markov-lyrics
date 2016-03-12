package markov.states;

public abstract class State<T> {
    public boolean canEqual(Object obj) {
        return false;
    }
}
