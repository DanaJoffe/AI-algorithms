// dana joffe 312129240

import java.util.List;
import java.util.stream.Collectors;

/**
 *  this class represents a generic state of a an uninformative search problem.
 * @param <T> a type that extends the GeneralState of itself. it represents an implementation of state logic,
 * dependent on a specific problem.
 */
public class State <T extends GeneralState<T>> {
    private T state;

    /**
     * constructor.
     * @param state - an implementation of state logic, dependent on a specific problem.
     */
    public State(T state) {
        this.state = state;
    }

    /**
     * @param o - a State<T> object.
     * @return true if the arguments are equal to each other and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof State<?>)) {
            return false;
        }
        State<?> stateO = (State<?>) o;
        return this.state.equals(stateO.state);
    }

    /**
     * @return a list of the successors = suns of current state = possible following states.
     */
    public List<State<T>> getSuccessors() {
        List<T> Tsucc = this.state.getSuccessors();
        return Tsucc.stream().map(item -> new State<>(item)).collect(Collectors.toList());
    }

    /**
     * @return a series of operations that will bring the initial state to a current state.
     */
    public String getPath() {
        return this.state.getPath();
    }

    /**
     * determines the Order ratio between this state and a given state t.
     * @param t - a state.
     * @return a negative integer, zero, or a positive integer as this is less than,
     * equal to, or greater than t.
     */
    public int compareTo(State<T> t) {
        return this.state.compareTo(t.state);
    }

    /**
     * @return the concrete implementation of state logic, that is within this state.
     */
    T getState() {
        return this.state;
    }
}
