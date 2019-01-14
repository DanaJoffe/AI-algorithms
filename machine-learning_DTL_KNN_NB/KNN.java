// dana joffe 312129240

import java.util.*;
import java.util.stream.Collectors;

/**
 * K-Nearest Neighbors algorithm implementation.
 */
public class KNN implements MachineLearningAlgo{
    private int k;
    private DistanceCalc distCalc;
    private IExamples trainData;

    /**
     * constructor.
     * @param trainData - data base of all examples for determine the classification.
     * @param k - odd number that specifies the amount of nearest neighbors neighbors to look for.
     * @param distCalc - function object that calculates the distance between two examples.
     */
    public KNN(IExamples trainData, int k, DistanceCalc distCalc) {
        this.trainData = trainData;
        this.distCalc = distCalc;
        this.k= k;
    }

    /**
     * @return the name of the algorithm.
     */
    public String getAlgoName() {
        return "KNN";
    }

    /**
     * @param testCases - data base of test cases that need to be classified.
     * @return list of pairs: (test case, it's classification).
     */
    public List<Pair<Example, String>> classifyExamples(IExamples testCases){
        return this.classifyExamples(this.trainData, testCases, this.k, this.distCalc);
    }

    /**
     *
     * @param trainData - data base of all examples for determine the classification.
     * @param testCases - data base of test cases that need to be classified.
     * @param k - odd number that specifies the amount of nearest neighbors neighbors to look for.
     * @param distCalc - function object that calculates the distance between two examples.
     * @return a list of pairs: (test case, it's classification).
     */
    private List<Pair<Example, String>> classifyExamples(IExamples trainData, IExamples testCases,
                                                        int k, DistanceCalc distCalc) {
        List<Pair<Example, String>> classifications = new ArrayList<Pair<Example, String>>();
        for(Example testCase: testCases) {
            classifications.add(new Pair<>(testCase, this.classifyTestCase(trainData, testCase, distCalc, k)));
        }
        return classifications;
    }

    /**
     * calculates the classifications of a given test case to be the classification of the majority
     * of its k-nearest examples.
     * @param trainData - data base of all examples for determine the classification.
     * @param testCase - test case that need to be classified.
     * @param distCalc - function object that calculates the distance between two examples.
     * @param k - odd number that specifies the amount of nearest neighbors neighbors to look for.
     * @return the classifications of the test case.
     */
    private String classifyTestCase(IExamples trainData, Example testCase, DistanceCalc distCalc, int k) {
        List<Pair<Float, Example>> distanceExamplePair = new ArrayList<Pair<Float, Example>>();
        // go over all examples and calculate the distance
        for (Example example: trainData){
            distanceExamplePair.add(new Pair<>(distCalc.calculateDistance(example, testCase), example));
        }
        // sort (stable sort) in ascending distance order
        distanceExamplePair.sort(new Comparator<Pair<Float, Example>>() {
            @Override
            public int compare(Pair<Float, Example> o1, Pair<Float, Example> o2) {
                float result = o1.getKey() - o2.getKey();
                if (result < 0) { return -1; }
                if(result > 0) { return 1; }
                return 0;
            }
        });
        // take the k-closest training examples and map them to their occurrence number
        Map<String, Long> kClosestClassifications = distanceExamplePair
                .subList(0,k)
                .stream()
                .map(floatExamplePair -> floatExamplePair.getValue().getClassification())
                .collect(Collectors.groupingBy(w ->w,  Collectors.counting()));
        try {
            return MaxFinder.findMaxByValueInMap(kClosestClassifications, true);
        } catch (Exception e) {
            // never gets here. there is always an absolute maximum
            return kClosestClassifications.entrySet().iterator().next().getKey();
        }
    }
}