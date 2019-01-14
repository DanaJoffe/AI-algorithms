// dana joffe 312129240

/**
 * this class is implementing the IDS search algorithm on general initial & goal states of a problem.
 * @param <T> a type that extends the GeneralState of itself.
 */
public class IDS <T extends GeneralState<T>> implements SearchAlgo<T>  {
    /**
     * constructor.
     */
    public IDS() {}

    /**
     *  solve the search problem using IDS: call DFS-L for every L, until a solution is found.
     * @param initState - initial state of a search problem.
     * @param goal - goal state of a search problem.
     * @return the solution of the search problem from initState to goal, and null if it doesn't exit.
     */
    public Solution search(State<T> initState, State<T> goal) {
        Solution sol;
        int level = 0;
        DFSL<T> dfsl;
        while (true) {
            dfsl = new DFSL<T>(level);
            sol = dfsl.search(initState, goal);
            if (sol!= null){ return sol; }
            level +=1 ;
        }
    }
}
