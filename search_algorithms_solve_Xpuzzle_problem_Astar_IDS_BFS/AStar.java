// dana joffe 312129240

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * this class is implementing the A* search algorithm on general initial & goal states of a problem.
 * @param <T> a type that extends the GeneralState of itself.
 */
public class AStar <T extends GeneralState<T>> implements SearchAlgo<T> {
    private HeuristicFunction<T> h;
    private Cost<T> c;
    private Comparator<InformativeState<T>> comperator;
    private int numOfNodesOpened;

    /**
     * constructor.
     * @param h - heuristic function.
     * @param c - arches cost calculator function.
     * @param comperator - determines order ratio between tso states.
     */
    public AStar(HeuristicFunction<T> h, Cost<T> c, Comparator<InformativeState<T>> comperator) {
        this.h = h;
        this.c = c;
        this.comperator = comperator;
        this.numOfNodesOpened = 0;
    }

    /**
     * solve the search problem using A* algorithm, with given heuristic & cost functions, and a comparator for the
     * pririty queue.
     * @param init - initial state of a search problem.
     * @param goal - goal state of a search problem.
     * @return the solution of the search problem from init to goal, and null if it doesn't exit.
     */
    public Solution search(State<T> init, State<T> goal) {
        InformativeState<T> initialInformativeState = new InformativeState<T>(init).setG(0);
        InformativeState<T> goalInformativeState = new InformativeState<T>(goal).setH(0);
        int timeStamp = 1;
        initialInformativeState.setTimestamp(timeStamp);
        PriorityQueue<InformativeState<T>> open = new PriorityQueue<InformativeState<T>>(this.comperator);
        open.add(initialInformativeState);
        while (!open.isEmpty()) {
            InformativeState<T> parentInformativeState = open.poll();
            this.numOfNodesOpened++;
            if (parentInformativeState.equals(goalInformativeState)) {
                return new Solution(parentInformativeState.getPath(), this.numOfNodesOpened,
                        parentInformativeState.getG());
            }
            timeStamp++;
            List<InformativeState<T>> successors = parentInformativeState.getSuccessors();
            for (InformativeState<T> sonInformativeState : successors) {
                // update h to be hFunc(sonInformativeState) regarding goalInformativeState
                sonInformativeState.setH(this.h.calcDistance(sonInformativeState, goalInformativeState));
                // update g to be parentInformativeState.g + 1
                sonInformativeState.setG(parentInformativeState.getG() +
                        this.c.calcCost(parentInformativeState, sonInformativeState));
                // initialise time stamp of creation
                sonInformativeState.setTimestamp(timeStamp);
                open.add(sonInformativeState);
            }
        }
        return null;
    }
}