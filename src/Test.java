import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import markov.parsers.NGramParser;
import markov.parsers.Parser;
import markov.states.NGramState;
import markov.states.State;
import markov.StateTransitions;

class Test {
    static final String _ALL_LYRICS = "/Users/vincent/projects/markov-lyrics/lyrics/";
    static final String _APPETITE_FOR_DESTRUCTION = "/Users/vincent/projects/markov-lyrics/lyrics/guns-n-roses/appetite-for-destruction";
    static final String _1989 = "/Users/vincent/projects/markov-lyrics/lyrics/taylor-swift/1989";

    public static void main(String[] args) {
        String directory = _APPETITE_FOR_DESTRUCTION;
        test4(directory);
    }

    public static void printLyrics(StateTransitions transitions, State initialState, int numWords) {
        NGramState currentState = (NGramState)initialState;

        for (int i = 0; i < numWords; i++) {
            currentState = (NGramState)transitions.getNextRandomState(currentState);
            if (currentState.getNGramSize() != 0) {
                System.out.print(currentState.getLastToken() + " ");
            }
        }

        System.out.println("\n");
    }

    public static List<File> getAllFiles(File file) {
        List<File> files = new ArrayList<>();
        if (file.isFile()) {
            files.add(file);
            return files;
        }

        for (File subFile : file.listFiles()) {
            if (subFile.isHidden()) {
                continue;
            }

            files.addAll(getAllFiles(subFile));
        }
        return files;
    }

    public static void test4(String directory) {
        try {

            File dir = new File(directory);

            StateTransitions transitions = new StateTransitions();
            State startState = new NGramState<String>(0, new ArrayList<>());// same as in ngramparser
            for (File f : getAllFiles(dir)) {
                if (f.isHidden()) {
                    continue;
                }

                System.out.println("parsing: " + f);
                Parser parser = new NGramParser(2);
                StateTransitions additionalTransitions = parser.parse(f);

                transitions.merge(additionalTransitions);
            }

            printLyrics(transitions, startState, 200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
