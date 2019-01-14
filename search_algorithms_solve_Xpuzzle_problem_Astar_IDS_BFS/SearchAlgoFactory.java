// dana joffe 312129240

import java.util.Comparator;

/**
 * a factory to create search algorithms.
 * @param <T> a type that extends the GeneralState of itself.
 */
public class SearchAlgoFactory<T extends GeneralState<T>> {
    private Graph<T> graph;
    private HeuristicFunction<T> h;
    private Comparator<InformativeState<T>> comp;

    /**
     * constructor.
     * @param g - a graph of problem type T.
     * @param h - a Heuristic Function for problem type T.
     * @param comp - a comparator for states of problem type T.
     */
    public SearchAlgoFactory (Graph<T> g, HeuristicFunction<T> h, Comparator<InformativeState<T>> comp) {
        this.graph = g;
        this.comp = comp;
        this.h = h;
    }

    /**
     * @param num - 1 for IDS, 2 for BFS, 3 for A*.
     * @return a search algorithm.
     */
    public SearchAlgo<T> createAlgo(int num) {
        switch (num) {
            case (1):
                return new IDS<T>();
            case (2):
                return new BFS<T>();
            case (3):
                return new AStar<T>(this.h, this.graph.getCostFunc(), this.comp);
            default:
                return null;
        }
    }
}