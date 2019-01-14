// dana joffe 312129240

/**
 * this interface specifies a graph that it's nodes are of type T.
 * @param <T> a type that extends the GeneralState of itself.
 */
public interface Graph <T extends GeneralState<T>> {
    /**
     * @return the head of the graph, the starting node.
     */
    State<T> getInitialState();

    /**
     * @return the goal node of the graph.
     */
    State<T> getGoalState();

    /**
     * @return a arches cost function.
     */
    Cost<T> getCostFunc();
}
