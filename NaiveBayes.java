// dana joffe 312129240

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NaiveBayes implements MachineLearningAlgo{
    private IExamples trainData;
    private List<IAttribute> attributes;

    public NaiveBayes(IExamples trainData, List<IAttribute> attributes) {
        this.attributes = attributes;
        this.trainData = trainData;
    }

    public String getAlgoName() {
        return "naiveBase";
    }

    public List<Pair<Example, String>> classifyExamples(IExamples testCases){
        return this.classifyExamples(this.trainData, testCases, this.attributes);
    }


    private List<Pair<Example, String>> classifyExamples(IExamples trainData, IExamples testCases,
                                                               List<IAttribute> attributes) {
        List<Pair<Example, String>> classifications = new ArrayList<Pair<Example, String>>();
        for(Example testCase: testCases) {
            classifications.add(new Pair<>(testCase, this.classifyTestCase(trainData, testCase, attributes)));
        }
        return classifications;
    }

    private String classifyTestCase(IExamples trainData, Example testCase, List<IAttribute> attributes) {
        Map<String, IAttribute> nameToAttribute = attributes.stream().collect(
                Collectors.toMap(IAttribute::getName, Function.identity()));

        Map<String, Long> allClassOccurrAmount= trainData.getAllClassOccurrAmount();
        double dataAmount = (double) trainData.size();
        Long clasAmount;

        // maps Cj classification to it's probability
        Map<String, Double> classToProb = new HashMap<String ,Double>();
        for (String clas: allClassOccurrAmount.keySet()) {
            // #{ci}
            clasAmount = allClassOccurrAmount.get(clas);
            classToProb.put(clas,
                    this.calcClassProb(clas, dataAmount, trainData, clasAmount, nameToAttribute, testCase));
        }
        // find the class with the maximum probability
        try {
            // there is an absolute maximum
            return new MaxFinder<String, Double>().findMaxByValueInMap(classToProb);
        } catch (Exception e) {
            // there are 2 equal probabilities
            try {
                return new MaxFinder<String, Long>().findMaxByValueInMap(allClassOccurrAmount, true);
            } catch (Exception e2) {
                // return the positive
                return Ex2Guidelines.getPositiveClass(classToProb.keySet());
            }
        }
    }

    private Double calcClassProb(String clas, double dataAmount, IExamples trainData, Long clasAmount,
                                 Map<String, IAttribute> nameToAttribute, Example testCase) {
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
