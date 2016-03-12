package markov.parsers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import markov.states.NGramState;
import markov.states.State;
import markov.StateTransitions;

public class NGramParser extends Parser {

    private final int nGramSize;

    public NGramParser(int nGramSize) {
        super();
        this.nGramSize = nGramSize;
    }

    @Override
    public State<String> getInitialState() {
        // TODO: Define this somewhere
        return new NGramState<String>(0, new ArrayList<>());
    }

    @Override
    public State<String> processToken(State currentState, String token) {
        if (!(currentState instanceof NGramState)) {
            throw new IllegalStateException("Invalid state: " + currentState);
        }

        // TODO: Support non-bigrams
        List<String> tokens = new ArrayList<>();
        tokens.add(token);

        return new NGramState<String>(1, tokens);
    }

}
