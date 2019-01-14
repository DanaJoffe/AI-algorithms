// dana joffe 312129240

/**
 * container for the search algorithm solution.
 */
public class Solution {
    private String path;
    private String openNodes;
    private String unique;

    /**
     * constructor.
     * @param path - a series of operations that bring the initial state to the goal state.
     * @param openNodes - how many states were pulled out (opened) from the 'open list'.
     * @param unique - a unique number for each algorithm:
     *               for BFS: unique = 0,
     *               for IDS: unique = goal state depth in the graph problem,
     *               for A*: unique = the cost of the goal state path.
     */
    public Solution(String path, int openNodes, int unique) {
        this.openNodes = Integer.toString(openNodes);
        this.path = path;
        this.unique = Integer.toString(unique);
    }

    /**
     * Getter.
     * @return the series of operations that brought the initial state to the goal state.
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Getter.
     * @return  how many states were pulled out (opened) from the 'open list'.
     */
    public String getOpenNodes() {
        return this.openNodes;
    }

    /**
     * Getter.
     * @return the unique number.
     */
    public String getUnique() {
        return this.unique;
    }

    /**
     * @return a string representation of the solution (to be written to file)
     */
    public String toString() {
        return this.path + " " + this.openNodes + " " + this.unique;
    }
}
