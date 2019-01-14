// dana joffe 312129240

/**
 * this interface specifies a type function that calculates the arch cost in a graph, for two informative nodes.
 * @param <T> a type that extends the GeneralState of itself.
 */
public interface Cost <T extends GeneralState<T>> {
    /**
     *
     * @param fromInformativeState a node(state) in a graph(search problem).
     * @param toInformativeState an option for the next node to come after fromInformativeState.
     * @return the cost of the arch between fromInformativeState to toInformativeState.
     */
    int calcCost(InformativeState<T> fromInformativeState, InformativeState<T> toInformativeState);
}
