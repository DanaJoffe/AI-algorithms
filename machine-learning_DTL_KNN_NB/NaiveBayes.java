// dana joffe 312129240

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Naive Bayes algorithm implementation.
 */
public class NaiveBayes implements MachineLearningAlgo{
    private IExamples trainData;
    private List<IAttribute> attributes;

    /**
     * constructor.
     * @param trainData - data base of all examples for determine the classification.
     * @param attributes - list of all existing attributes in examples, and their possible values.
     */
    public NaiveBayes(IExamples trainData, List<IAttribute> attributes) {
        this.attributes = attributes;
        this.trainData = trainData;
    }

    /**
     * @return the name of the algorithm.
     */
    public String getAlgoName() {
        return "naiveBase";
    }

    /**
     * @param testCases - data base of test cases that need to be classified.
     * @return list of pairs: (test case, it's classification).
     */
    public List<Pair<Example, String>> classifyExamples(IExamples testCases){
        return this.classifyExamples(this.trainData, testCases, this.attributes);
    }

    /**
     *
     * @param trainData - data base of all examples for determine the classification.
     * @param testCases - data base of test cases that need to be classified.
     * @param attributes - list of all existing attributes in examples, and their possible values.
     * @return list of pairs: (test case, it's classification).
     */
    private List<Pair<Example, String>> classifyExamples(IExamples trainData, IExamples testCases,
                                                               List<IAttribute> attributes) {
        List<Pair<Example, String>> classifications = new ArrayList<Pair<Example, String>>();
        for(Example testCase: testCases) {
            classifications.add(new Pair<>(testCase, this.classifyTestCase(trainData, testCase, attributes)));
        }
        return classifications;
    }

    /**
     * calculates the probability for each possible classification, and returns the class with the highest probability.
     * @param trainData - data base of all examples for determine the classification.
     * @param testCase - test case that need to be classified.
     * @param attributes - list of all existing attributes in examples, and their possible values.
     * @return the classifications of the test case.
     */
    private String classifyTestCase(IExamples trainData, Example testCase, List<IAttribute> attributes) {
        Map<String, IAttribute> nameToAttribute = attributes.stream().collect(
                Collectors.toMap(IAttribute::getName, Function.identity()));
        Map<String, Long> allClassOccurrAmount= trainData.getAllClassOccurrAmount();
        Long clasAmount;

        // maps Cj classification to it's probability
        Map<String, Double> classToProb = new HashMap<String ,Double>();
        for (String clas: allClassOccurrAmount.keySet()) {
            // #{ci}
            clasAmount = allClassOccurrAmount.get(clas);
            classToProb.put(clas,
                    this.calcClassProb(clas, trainData, clasAmount, nameToAttribute, testCase));
        }
        // find the class with the maximum probability
        try {
            // there is an absolute maximum
            return MaxFinder.findMaxByValueInMap(classToProb);
        } catch (Exception e) {
            // there are 2 equal probabilities
            try {
                return MaxFinder.findMaxByValueInMap(allClassOccurrAmount, true);
            } catch (Exception e2) {
                // return the positive
                return Ex2Guidelines.getPositiveClass(classToProb.keySet());
            }
        }
    }

    /**
     * calculates p(C=ci and Xi=xi) whereas ci = clas, xi = all testCase attribute' values.
     * @param clas - a classification.
     * @param trainData - data base of all examples for determine the classification.
     * @param clasAmount - how many times the given clas occurs in trainData.
     * @param nameToAttribute a map of: attributes' name -> its corresponding object.
     * @param testCase - test case that need to be classified.
     * @return the probability of a clas to be the classification, given all the attribute' values of a testCase.
     */
    private Double calcClassProb(String clas, IExamples trainData, Long clasAmount,
                                 Map<String, IAttribute> nameToAttribute, Example testCase) {
        double dataAmount = (double) trainData.size();
        // p(ci)
        double pClas = (double)clasAmount / dataAmount;
        int Xi_eq_xi_and_C_eq_ci_amount, k;
        double result = 1, p_Xi_Given_ci;

        // p(xi|ci)
        for (String attribute : nameToAttribute.keySet()) {
            Xi_eq_xi_and_C_eq_ci_amount = trainData.filter(example ->
                    // C = ci && Xi = xi
                    example.getClassification().equals(clas) &&
                            example.getValue(attribute).equals(testCase.getValue(attribute))).size();
            k = nameToAttribute.get(attribute).getAllPossibleValues().size();
            p_Xi_Given_ci = (double) (Xi_eq_xi_and_C_eq_ci_amount + 1) / (double)(clasAmount + k);
            result *= p_Xi_Given_ci;
        }
        result *= pClas;
        return result;
    }
}