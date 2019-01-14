// dana joffe 312129240

/**
 * this interface specifies a heuristic function for generic informative states.
 * @param <T> a type that extends the GeneralState of itself.
 */
public interface HeuristicFunction <T extends GeneralState<T>> {
    /**
     * calculates the heuristic estimation of path cost from informativeState to goal.
     * @param informativeState - a state that we have information about.
     * @param goal - a goal state that we have information about.
     * @return the estimation of the distance from informativeState to goal.
     */
    int calcDistance(InformativeState<T> informativeState, InformativeState<T> goal);
}
