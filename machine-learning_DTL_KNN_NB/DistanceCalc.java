// dana joffe 312129240

/**
 * function class that calculates the distance between two examples.
 * can be implemented as Hamming distance, euclidean distance, ect.
 */
public interface DistanceCalc {
    /**
     * @param ex1 - example object
     * @param ex2 - example object
     * @return the distance between ex1, ex2
     */
    float calculateDistance(Example ex1, Example ex2);
}
