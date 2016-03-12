package markov;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import markov.states.State;

public class StateDistribution {
    private Map<State, Integer> stateCounts = new LinkedHashMap<>();
    int totalCount = 0;

    public void incrementStateCount(State state) {
        incrementStateCount(state, 1);
    }

    private void incrementStateCount(State state, int increment) {
        if (!stateCounts.containsKey(state)) {
            stateCounts.put(state, 0);
        }

        stateCounts.put(state, stateCounts.get(state) + increment);
        totalCount += increment;
    }

    public State getRandomState() {
        int randomIndex = getRandomIndex();
        return getStateAtIndex(randomIndex);
    }

    private int getRandomIndex() {
        Random random = new Random();
        return random.nextInt(totalCount);
    }

    private State getStateAtIndex(int index) {
        int count = 0;
        for (Map.Entry<State, Integer> entry : stateCounts.entrySet()) {
            count += entry.getValue();
            if (count > index) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void merge(StateDistribution otherDistribution) {
        for (Map.Entry<State, Integer> entry : otherDistribution.stateCounts.entrySet()) {
            incrementStateCount(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public String toString() {
        return stateCounts.toString();
    }
}
