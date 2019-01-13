// dana joffe 312129240

import java.util.*;
import java.util.stream.Collectors;

public class KNN implements MachineLearningAlgo{
    private int k;
    private DistanceCalc distCalc;
    private IExamples trainData;

    public KNN(IExamples trainData, int k, DistanceCalc distCalc) {
        this.trainData = trainData;
        this.distCalc = distCalc;
        this.k= k;
    }

    public String getAlgoName() {
        return "KNN";
    }

    public List<Pair<Example, String>> classifyExamples(IExamples testCases){
        return this.classifyExamples(this.trainData, testCases, this.k, this.distCalc);
    }

    private List<Pair<Example, String>> classifyExamples(IExamples trainData, IExamples testCases,
                                                        int k, DistanceCalc distCalc) {
        List<Pair<Example, String>> classifications = new ArrayList<Pair<Example, String>>();
        for(Example testCase: testCases) {
            classifications.add(new Pair<>(testCase, this.classifyTestCase(trainData, testCase, distCalc, k)));
        }
        return classifications;
    }

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

        Map.Entry<String, Long> mostCommonExample = null;
        for (Map.Entry<String, Long> entry : kClosestClassifications.entrySet()) {
            if (mostCommonExample == null || entry.getValue() > mostCommonExample.getValue()) {
                mostCommonExample = entry;
            }
        }
        return mostCommonExample.getKey();
    }

}
