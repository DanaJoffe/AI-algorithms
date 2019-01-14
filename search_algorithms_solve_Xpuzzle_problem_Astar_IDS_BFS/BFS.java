// dana joffe 312129240

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * this class is implementing the Breadth-first search  algorithm on general initial & goal states of a problem.
 * @param <T> a type that extends the GeneralState of itself.
 */
public class BFS  <T extends GeneralState<T>> implements SearchAlgo<T> {
    private int numOfNodesOpened;

    /**
     * constructor.
     */
    public BFS () {
        this.numOfNodesOpened = 0;
    }

    /**
     * solve the search problem using BFS algorithm.
     * @param init - initial state of a search problem.
     * @param goal - goal state of a search problem.
     * @return the solution of the search problem from init to goal, and null if it doesn't exit.
     */
    public Solution search(State<T> init, State<T> goal) {
        Queue<State<T>> open = new ArrayDeque<State<T>>();
        open.add(init);
        while (!open.isEmpty()) {
            State<T> state = open.poll();
            this.numOfNodesOpened ++;
            if (state.equals(goal)) {
                return new Solution(state .getPath(), this.numOfNodesOpened, 0);
            }
            open.addAll(state.getSuccessors());
        }
        return null;
    }
}
