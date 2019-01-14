// dana joffe 312129240

import java.util.Comparator;

/**
 * main class: contains the flow of the program: reads an "input.txt" file, creates a graph (arches costs function,
 * initial state & goal state), creates (using Algorithm factory) the wanted search algorithm and search for solution
 * path in the graph. the search algorithm returns a Solution object that is being written to an output file.
 */
public class java_ex1 {
    public static void main(String[] args) {

        String inputFileName = "input.txt";
        String outputFileName = "output.txt";

        InputFile inf = FileManagement.readFile(inputFileName);

        Graph<PuzzleGeneralState> graph = new PuzzleGraph(inf.getInitialState(), inf.getPuzzleSize());

        SearchAlgoFactory<PuzzleGeneralState> saf = new SearchAlgoFactory<>(
                graph,
                new HeuristicFunction<PuzzleGeneralState>() {
                    /**
                     * Heuristic Function - calculate sum of manhattan distances.
                     * @param state - a state of the puzzle.
                     * @param goal - the goal state of the puzzle.
                     * @return sum of manhattan distances of every square from 'state' to 'goal'.
                     */
                    @Override
                    public int calcDistance(InformativeState<PuzzleGeneralState> state,
                                            InformativeState<PuzzleGeneralState> goal) {
                        PuzzleGeneralState ps = state.getState();
                        PuzzleGeneralState psGoal = goal.getState();
                        int[] stateIndexes, goalIndexes;
                        int sumOfManDist = 0;
                        int n = ps.getPuzzleSize();
                        for (int num=1; num < n*n; num++) {
                            stateIndexes = ps.getIndexesOf(num);
                            goalIndexes = psGoal.getIndexesOf(num);
                            sumOfManDist += Math.abs(stateIndexes[0] - goalIndexes[0]) +
                                    Math.abs(stateIndexes[1] - goalIndexes[1]);
                        }
                        return sumOfManDist;
                    }
                },
                new Comparator<InformativeState<PuzzleGeneralState>>() {
                    /**
                     * determines the Order ratio between two states of the puzzle. the rules:
                     * 1. state with the lowest f value is first.
                     * 2. (states with equal f values) state with the earliest time stamp is first.
                     * 3. (states with equal time stamps) state with the lowest Operator is first, when the order
                     * is : U < D < L < R.
                     * @param st1 - state of the puzzle.
                     * @param st2 - another state of the puzzle.
                     * @return a negative integer, zero, or a positive integer as the first argument is less than,
                     * equal to, or greater than the second.
                     */
                    @Override
                    public int compare(InformativeState<PuzzleGeneralState> st1,
                                       InformativeState<PuzzleGeneralState> st2) {
                        if (st1 == st2) {
                            return 0;
                        }
                        // if f values are not equal - they determine the order.
                        if (st1.f() - st2.f() != 0) { return st1.f() - st2.f(); }
                        // f values are equal. if Timestamps are not equal - they determine the order.
                        if (st1.getTimestamp() - st2.getTimestamp() != 0) {
                            return st1.getTimestamp() - st2.getTimestamp(); }
                        // timestamps are equal. PuzzleState determines the order.
                        return st1.getState().compareTo(st2.getState());
                    }
                });

        SearchAlgo<PuzzleGeneralState> searchAlgo = saf.createAlgo(inf.getAlgoNumber());

        Solution sol = searchAlgo.search(graph.getInitialState(), graph.getGoalState());

        FileManagement.writeToFile(outputFileName, sol.toString());
    }
}
