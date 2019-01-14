// dana joffe 312129240

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * run all algorithms on given test cases and creates for each algorithm a performance object.
 */
public class AlgoTester {
    // supervised learning
    private List<MachineLearningAlgo> algorithms;

    /**
     * constructor.
     */
    public AlgoTester() {
        algorithms = new ArrayList<MachineLearningAlgo>();
    }

    /**
     * add algorithm to the tested algorithms' list.
     * @param algorithm - a Machine Learning algorithm.
     */
    public void addAlgo(MachineLearningAlgo algorithm) {
        this.algorithms.add(algorithm);
    }

    /**
     *  run all algorithms on given test cases and creates for each algorithm a performance object.
     * @param examples - test cases to run the algorithms on.
     * @return a list of the performances of all algorithms.
     */
    public List<AlgoPerformance> testAllAlgos(IExamples examples) {
        List<Pair<Example, String>> classifications;
        List<AlgoPerformance> performances = new ArrayList<AlgoPerformance>();

        for (MachineLearningAlgo algo: this.algorithms) {
            classifications = algo.classifyExamples(examples);
            performances.add(new AlgoPerformance(algo.getAlgoName(), this.getAccuracy(classifications),
                    classifications
                            .stream()
                            .map(Pair<Example, String>::getValue)
                            .collect(Collectors.toList())));
        }
        return performances;
    }

    /**
     * helper function that calculates the accuracy of an algorithm.
     * @param classifications - list of (test case, it's estimated classification) pairs.
     * @return - the accuracy percentage of the estimated classifications.
     */
    private double getAccuracy(List<Pair<Example, String>> classifications) {
        double examplesQuantity = (double) classifications.size();
        double rightClassQuantity = 0;
        for (Pair<Example, String> exmClsPair: classifications) {
            if (exmClsPair.getKey().getClassification().equals(exmClsPair.getValue())) {
                rightClassQuantity ++;
            }
        }
        return rightClassQuantity/examplesQuantity;
    }
}