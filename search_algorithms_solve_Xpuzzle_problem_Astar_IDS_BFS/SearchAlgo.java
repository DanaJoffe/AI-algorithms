// dana joffe 312129240

/**
 * this interface specifies search algorithms: a type of algorithms that with given initial & goal states, can
 * find a solution = a path from initial state to goal state.
 * @param <T> a type that extends the GeneralState of itself.
 */
public interface SearchAlgo <T extends GeneralState<T>> {
    /**
     * solve the search problem using some search algorithm.
     * @param initState - initial state of a search problem.
     * @param goal - goal state of a search problem.
     * @return the solution of the search problem from init to goal, and null if it doesn't exit.
     */
    Solution search(State<T> initState, State<T> goal);
}
