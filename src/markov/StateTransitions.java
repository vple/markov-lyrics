package markov;

import java.util.HashMap;
import java.util.Map;

import markov.StateDistribution;
import markov.states.State;

public class StateTransitions {
    private Map<State, StateDistribution> transitions = new HashMap<>();

    public State getNextRandomState(State currentState) {
        return transitions.get(currentState).getRandomState();
    }

    public void recordTransition(State currentState, State nextState) {
        StateDistribution distribution = getDistributionForState(currentState);
        distribution.incrementStateCount(nextState);
    }

    private StateDistribution getDistributionForState(State state) {
        StateDistribution distribution = transitions.get(state);
        if (distribution == null) {
            distribution = new StateDistribution();
            transitions.put(state, distribution);
        }
        return distribution;
    }

    public void merge(StateTransitions otherTransitions) {
        for (Map.Entry<State, StateDistribution> entry : otherTransitions.entrySet()) {
            StateDistribution distribution = getDistributionForState(entry.getKey());
            distribution.merge(entry.getValue());
        }
    }

    @Override
    public String toString() {
        return transitions.toString();
    }
}
