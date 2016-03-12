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
    public static void main(String[] args) {
        test3();
    }

    public static void test3() {

        String directory = "/Users/vincent/projects/markov-lyrics/lyrics/taylor-swift/1989/";
        try {
            File dir = new File(directory);


            StateTransitions transitions = new StateTransitions();
            State startState = new NGramState<String>(1, new ArrayList<>());
            State currentState = startState;
            for (File f : dir.listFiles()) {
                if (f.isHidden()) {
                    continue;
                }
                System.out.println("parsing: " + f);

                currentState = startState;
                Scanner scanner = new Scanner(f);
                while (scanner.hasNext()) {
                    String token = scanner.next();
                    List<String> tokens = new ArrayList<>();
                    tokens.add(token);

                    State state = new NGramState<String>(1, tokens);
                    transitions.recordTransition(currentState, state);
                    currentState = state;
                }
            }

            //System.out.println(transitions);

            System.out.println("\n");

            NGramState currentNGram = (NGramState) startState;
            for (int i = 0; i < 200; i++) {
                currentNGram = (NGramState)transitions.getNextRandomState(currentNGram);
                System.out.print(currentNGram.getTokens().get(0) + " ");
            }
            System.out.println("\n");
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void test2() {
        Parser parser = new NGramParser(3);

        List<String> strings = new ArrayList<>();
        State<String> state = new NGramState<>(1, strings);
    }

    public static void test1() {
        String path = "/Users/vincent/projects/markov-lyrics/lyrics/taylor-swift/1989/02-blank-space.txt";
        String directory = "/Users/vincent/projects/markov-lyrics/lyrics/taylor-swift/1989/";
        try {
            File dir = new File(directory);

            Map<String, Integer> wordCount = new HashMap<>();
            List<String> words = new ArrayList<>();

            for (File f : dir.listFiles()) {
                System.out.println("Parsing: " + f);
                Scanner scanner = new Scanner(f);
                int numTokens = 0;
                while (scanner.hasNext()) {
                    String token = scanner.next();
                    int count = wordCount.containsKey(token) ? wordCount.get(token) : 0;
                    wordCount.put(token, count+1);
                    words.add(token);
                    numTokens++;
                }
            }

            System.out.println(wordCount + "\n");

            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                System.out.print(words.get(random.nextInt(words.size())) + " ");
            }
            System.out.println("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
