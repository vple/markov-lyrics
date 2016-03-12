package markov.states;

import java.util.Arrays;
import java.util.List;

public class NGramState<T> extends State {

    private final int nGramSize;
    private final List<T> tokens;

    public NGramState(int nGramSize, List<T> tokens) {
        this.nGramSize = nGramSize;
        // TODO: ImmutableList.of(tokens)
        this.tokens = tokens;
    }

    public List<T> getTokens() {
        return tokens;
    }

    public T getLastToken() {
        return tokens.get(tokens.size() - 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NGramState<?>)) {
            return false;
        }
        NGramState<?> other = (NGramState<?>)obj;
        if (!other.canEqual(this)) {
            return false;
        }
        return nGramSize == other.nGramSize && tokens.equals(other.tokens);
    }

    @Override
    public boolean canEqual(Object obj) {
        return obj instanceof NGramState<?>;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(tokens.toArray());
    }

    @Override
    public String toString() {
        return tokens.toString();
    }
}
