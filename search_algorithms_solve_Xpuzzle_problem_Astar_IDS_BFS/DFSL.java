// dana joffe 312129240

import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * this class is implementing the DFS-L search algorithm on general initial & goal states of a problem.
 * @param <T>  a type that extends the GeneralState of itself.
 */
public class DFSL <T extends GeneralState<T>> {
    private int maxDepth;
    private int numOfNodesOpened;

    /**
     * constructor.
     * @param maxDepth
     */
    public DFSL(int maxDepth) {
        this.maxDepth = maxDepth;
        this.numOfNodesOpened = 0;
    }

    /**
     * solve the search problem using DFSL recursive algorithm, with pre-defined Level.
     * @param state - initial state of a search problem.
     * @param goal - goal state of a search problem.
     * @return the solution of the search problem from init to goal, and null if it doesn't exit.
     */
    public Solution search(State<T> state, State<T> goal) {
        return this.DFSLalgo(state, goal, this.maxDepth);
    }

    /**
     * this function calls to itself recursively with a smaller level in each iteration, to solve the search problem.
     * @param initState - initial state of a search problem.
     * @param goal - goal state of a search problem.
     * @param depth - tree level that is the limit of this search.
     * @return the solution of the search problem from initState to goal, and null if it doesn't exit.
     */
    private Solution DFSLalgo(State<T> initState, State<T> goal, int depth) {
        Solution solution;
        State<T> state;
        this.numOfNodesOpened += 1;
        if (initState.equals(goal)) {
            return new Solution(initState.getPath(), this.numOfNodesOpened, this.maxDepth - depth);
        }
        if (depth == 0) { return null; }
        Stack<State<T>> open = new Stack<State<T>>();
        List<State<T>> successors = initState.getSuccessors();
        Collections.reverse(successors);
        open.addAll(successors);
        while(!open.isEmpty()) {
            state = open.pop();
            solution = this.DFSLalgo(state, goal, depth-1);
            if(solution != null) {
                return solution;
            }
        }
        return null;
    }
}