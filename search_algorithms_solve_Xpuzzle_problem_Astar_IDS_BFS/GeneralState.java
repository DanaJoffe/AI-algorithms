// dana joffe 312129240

import java.util.List;

/**
 * this interface specifies a type of state that:
 * can return a list of successors, when each successor is the same type as the creator state,
 * can return the path to its creation,
 * is comparable to itself.
 * @param <T> a type that extends the GeneralState of itself.
 */
public interface GeneralState<T extends GeneralState<T>> extends Comparable<T> {
    /**
     * calculates the following possible states for all operators available.
     * @return a list of the successors = suns of current state.
     */
    List<T> getSuccessors();

    /**
     * @return A series of operations that will bring the initial state to a current state.
     */
    String getPath();
}

