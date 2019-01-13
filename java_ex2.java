// dana joffe 312129240

import java.util.*;

public class java_ex2 {
    public static void main(String[] args) {
        IExamples examples = new DataSet(FileManagement.readFile("train.txt", "\t"));
        List<IAttribute> attributes = examples.getAttributes();

        ID3 id3 = new ID3();
        NaiveBayes nb = new NaiveBayes(examples, attributes);
        KNN knn = new KNN(examples, 5, new DistanceCalc() {
            @Override
            public float calculateDistance(Example ex1, Example ex2) {
                // calculates Hamming Distance
                int distance = 0;
                String attributeName;
                for (IAttribute attribute: attributes) {
                    attributeName = attribute.getName();
                    if (! ex1.getValue(attributeName).equals(ex2.getValue(attributeName))) {
                        distance++;
                    }
                }
                return distance;
            }
        });

        DecisionTree dt = id3.DTL(examples, attributes, id3.MODE(examples));

        AlgoTester algoTester = new AlgoTester();
        algoTester.addAlgo(id3);
        algoTester.addAlgo(knn);
        algoTester.addAlgo(nb);

        IExamples testExamples = new DataSet(FileManagement.readFile("test.txt", "\t"));
        List<AlgoPerformance> performances = algoTester.testAllAlgos(testExamples);

        FileManagement.writeToFile("output.txt", Ex2Guidelines.organisePerformanceOutput(performances));
        FileManagement.writeToFile("output_tree.txt", Ex2Guidelines.organiseTreeOutput(dt.toString("")));
    }
}
