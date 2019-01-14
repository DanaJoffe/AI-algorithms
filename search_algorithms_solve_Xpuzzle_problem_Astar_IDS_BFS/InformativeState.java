// dana joffe 312129240

import java.util.List;
import java.util.stream.Collectors;

/**
 * this class represents a generic state of a an informative search problem.
 * InformativeState uses Decorator pattern to add information to an uninformative state.
 * @param <T> a type that extends the GeneralState of itself.
 */
public class InformativeState<T extends GeneralState<T>> {
    private State<T> tState;
    private int g;
    private int h;
    private int timestamp;

    /**
     * constructor.
     * @param state - a generic uninformative state.
     */
    public InformativeState(State<T> state) {
        this.tState = state;
    }

    /**
     * @param o should be InformativeState object.
     * @return true if this and o contains the same state info.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof InformativeState)) {
            return false;
        }
        InformativeState<?> informativeStateO = (InformativeState<?>) o;
        return this.tState.equals(informativeStateO.tState);
    }

    /**
     * @return a list of the successors = suns of current state = possible following states.
     */
    public List<InformativeState<T>> getSuccessors() {
        List<State<T>> l = this.tState.getSuccessors();

//        List<InformativeState<T>> newList = new ArrayList<InformativeState<T>>();
//        for(State<T> t: l) {
//            newList.add(new InformativeState<T>(t));
//        }
        return l.stream().map(item -> new InformativeState<>(item)).collect(Collectors.toList());
    }

    /**
     * @return a series of operations that will bring the initial state to a current state.
     */
    public String getPath() {
        return this.tState.getPath();
    }

    /**
     * determines the Order ratio between this state and a given state t.
     * @param t - a state.
     * @return a negative integer, zero, or a positive integer as this is less than,
     * equal to, or greater than t.
     */
    public int compareTo(InformativeState<T> t) {
        return this.tState.compareTo(t.tState);
    }


    // special functions
    // Setters

    /**
     * @param h - a heuristic value for this state.
     * @return this.
     */
    public InformativeState<T> setH(int h) {
        this.h = h;
        return this;
    }

    /**
     * @param g - the distance from initial state to this state.
     * @return this.
     */
    public InformativeState<T> setG(int g) {
        this.g = g;
        return this;
    }

    /**
     * time stamp sets a creation order between InformativeStates.
     * @param timestamp - integer that tells when (in an existing order) this state was created.
     * @return this.
     */
    public InformativeState<T> setTimestamp(int timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    // Getters

    /**
     * @return ths f value of this state: g value + h value.
     */
    public int f() {
        return this.g + this.h;
    }

    /**
     * @return this g value.
     */
    public int getG() {
        return this.g;
    }

    /**
     * @return the uninformed state within this state.
     */
    public T getState() {
        return this.tState.getState();
    }

    /**
     * @return the time stamp of this state = time of creation.
     */
    public int getTimestamp() {
        return this.timestamp;
    }
}