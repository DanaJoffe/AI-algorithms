// dana joffe 312129240

import java.util.List;

/**
 * container class that holds all the information (algo name, output, accuracy)
 * about the performance on some algorithm.
 */
public class AlgoPerformance {
    private String algoName;
    private List<String> orderedCls;
    private double accuracy;

    /**
     * @param algoName - the name of the algorithm.
     * @param accuracy - the accuracy of the algorithm.
     * @param orderedCls - the running results of the algorithm.
     */
    public AlgoPerformance(String algoName, double accuracy, List<String> orderedCls) {
        this.accuracy = accuracy;
        this.algoName = algoName;
        this.orderedCls = orderedCls;
    }

    /**
     * getter.
     * @return the name of the algorithm.
     */
    public String getAlgoName() {
        return algoName;
    }

    /**
     * getter.
     * @return the results of the algorithm.
     */
    public List<String> getOrderedCls() {
        return orderedCls;
    }

    /**
     * getter.
     * @return the accuracy of the algorithm.
     */
    public double getAccuracy() {
        return accuracy;
    }
}