// dana joffe 312129240

/**
 * this class represents a puzzle graph that supplies initial & goal states, and an arches cost function.
 */
public class PuzzleGraph implements Graph<PuzzleGeneralState> {
    private PuzzleGeneralState initialState;
    private PuzzleGeneralState goalState;

    /**
     * constructor.
     * @param initialState - matrix representation of the puzzle's initial state.
     * @param size - size of the puzzle.
     */
    public PuzzleGraph(int[][] initialState, int size) {
        this.initialState = new PuzzleGeneralState(initialState);
        int[][] goalState = new int[size][size];
        int num = 1;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; j++) {
                goalState[i][j] = num;
                num++;
            }
        }
        goalState[size-1][size-1] = 0;
        this.goalState = new PuzzleGeneralState(goalState);
    }

    /**
     * @return a State that contains the puzzle's initial state.
     */
    public State<PuzzleGeneralState> getInitialState() {
        return new State<PuzzleGeneralState>(this.initialState);
    }

    /**
     * @return a State that contains the puzzle's goal state.
     */
    public State<PuzzleGeneralState> getGoalState() {
        return new State<PuzzleGeneralState>(this.goalState);
    }

    /**
     * @return an arches cost calculator function.
     */
    public Cost<PuzzleGeneralState> getCostFunc() {
        return new Cost<PuzzleGeneralState>() {
            @Override
            public int calcCost(InformativeState<PuzzleGeneralState> fromState,
                                InformativeState<PuzzleGeneralState> toState) {
                PuzzleGeneralState s1 = fromState.getState();
                PuzzleGeneralState s2 = toState.getState();
                if (s1.getParent() != s2 && s2.getParent() != s1) { return Integer.MAX_VALUE;}
                // all the arches have value of 1.
                return 1;
            }
        };
    }
}