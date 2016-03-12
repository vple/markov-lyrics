package markov.parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import markov.states.NGramState;
import markov.states.State;
import markov.StateTransitions;

public abstract class Parser {

    public StateTransitions parse(File file)
    throws FileNotFoundException {
        StateTransitions transitions = new StateTransitions();

        Scanner scanner = new Scanner(file);
        State currentState = getInitialState();
        while (scanner.hasNext()) {
            State nextState = processToken(currentState, scanner.next());
            if (nextState != null) {
                transitions.recordTransition(currentState, nextState);
                currentState = nextState;
            }
        }

        return transitions;
    }

    public abstract State<String> getInitialState();

    public abstract State<String> processToken(State currentState, String token);
}
