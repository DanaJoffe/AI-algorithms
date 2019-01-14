// dana joffe 312129240

import java.util.*;

/**
 * ID3 implementation of Decision Tree algorithm.
 */
public class ID3 implements MachineLearningAlgo{
    // determines after the call to DTL.
    private DecisionTree decisionTree;

    /**
     * constructor.
     */
    public ID3() {}

    /**
     * @return the name of the algorithm.
     */
    public String getAlgoName() {
        return "DT";
    }

    /**
     * classify test cases, using the Decision Tree that was built in DTL function.
     * @param testCases - data base of test cases that need to be classified.
     * @return list of pairs: (test case, it's classification).
     */
    public List<Pair<Example, String>> classifyExamples(IExamples testCases){
        if (this.decisionTree == null) {
            return null;
        }
        return this.classifyExamples(testCases, this.decisionTree);
    }

    /**
     * @param examples - data base of all examples to build the dtl from.
     * @param attributes - list of all relevant attributes in examples, and their possible values.
     * @param defult - the default classification = the classification that appears most often in examples.
     * @return a Decision Tree to classify test cases.
     */
    public DecisionTree DTL(IExamples examples, List<IAttribute> attributes, DecisionTree defult) {
        if (examples.isEmpty()) {
            return defult;
        }
        Set<String> allClasses = examples.getAllExistingUniqueClasses();
        // if all examples have the same classification
        if (allClasses.size() == 1) {
            return new ClassificationNode(allClasses.iterator().next());
        }
        if (attributes.isEmpty()) {
            return this.MODE(examples);
        }
        IAttribute best = this.ChooseAttribute(examples, attributes);
        DecisionTree tree = new AttributeNode(best.getName());
        for (String value: best.getAllPossibleValues()) {
            // include only examples where best attribute is value
            IExamples bestEqValueExamples = examples.filter(example -> example.getValue(best.getName()).equals(value));
            List<IAttribute> attributesWithoutBest = new ArrayList<IAttribute>(attributes);
            attributesWithoutBest.remove(best);
            DecisionTree subtree = this.DTL(bestEqValueExamples, attributesWithoutBest, this.MODE(examples));
            try {
                tree.addSubTree(value, subtree);
            } catch (Exception e) { System.out.println("Exception was thrown: " + e.getMessage());};
        }
        this.decisionTree = tree;
        return tree;
    }

    /**
     * @param examples - data base of all current examples.
     * @return the classification that appears most often in examples.
     */
    public DecisionTree MODE(IExamples examples) {
        Map<String, Long> occurrences = examples.getAllClassOccurrAmount();
        String classification;
        try {
            classification = MaxFinder.findMaxByValueInMap(occurrences);
        } catch (Exception e) {
            // return the positive
            classification = Ex2Guidelines.getPositiveClass(occurrences.keySet());
        }
        return new ClassificationNode(classification);
    }

    /**
     * classify test cases, using a Decision Tree .
     * @param testCases - data base of all test cases to classify.
     * @param decisionTree - a decision tree to use in the classification process.
     * @return a list of pairs: (test case, it's classification).
     */
    private List<Pair<Example, String>> classifyExamples(IExamples testCases, DecisionTree decisionTree) {
        List<Pair<Example, String>> classifications = new ArrayList<Pair<Example, String>>();
        testCases.forEach(testCase ->
                classifications.add(new Pair<>(testCase, this.classifyTestCase(testCase, decisionTree))));
        return classifications;
    }

    /**
     * classify one test case, using a Decision Tree.
     * @param testCase - an Example object to classify.
     * @param decisionTree - a decision tree to use in the classification process.
     * @return - the test case's classification according to the decisionTree.
     */
    private String classifyTestCase(Example testCase, DecisionTree decisionTree) {
        DecisionTree node = decisionTree;
        DecisionTree classification = decisionTree;
        while (node!= null) {
            classification = node;
            node = node.getSubTree(testCase.getValue(node.getHeadName()));
        }
        return classification.getHeadName();
    }

    /**
     * part of the ID3 algorithm: choose the 'best' attribute from the given list, by it's highest information gain.
     * @param examples - data base of all current examples.
     * @param attributes - list of all relevant attributes in examples, and their possible values.
     * @return the attribute with the highest information gain.
     */
    private IAttribute ChooseAttribute(IExamples examples, List<IAttribute> attributes) {
        // maps every attribute to it's gain
        List<Pair<IAttribute, Float>> allIG = new ArrayList<Pair<IAttribute, Float>>();
        attributes.forEach(attribute -> allIG.
                add(new Pair<IAttribute, Float>(attribute, this.informationGain(examples, attribute))));
        // find the attribute with the maximum IG
        try {
            return MaxFinder.findMaxByValueInPairList(allIG, true);
        } catch (Exception e) { return null; }
    }

    /**
     * part of the ID3 algorithm: calculates the information gain of an attribute, given a set of examples.
     * @param examples - data base of all current examples.
     * @param attribute - an attribute and its possible values.
     * @return the information gain of the given attribute.
     */
    private float informationGain(IExamples examples, IAttribute attribute) {

        float informationGain = this.entropy(examples), ratio;
        for (String value: attribute.getAllPossibleValues()) {
            IExamples attrEqValueExamples = examples.filter
                    (example -> example.getValue(attribute.getName()).equals(value));
            ratio = (float)attrEqValueExamples.size()/(float) examples.size();
            informationGain -= ratio * this.entropy(attrEqValueExamples);
        }
        return informationGain;
    }

    /**
     * part of the ID3 algorithm: calculates the entropy of a set of examples.
     * @param examples - data base of all current examples.
     * @return the entropy of examples, as specified in the id3 algorithm.
     */
    private float entropy(IExamples examples) {
        Map<String, Long> classesOccurrences = examples.getAllClassOccurrAmount();
        float entropy = 0, p, totalSize = (float)examples.size();
        for (Map.Entry<String, Long> classOccurr : classesOccurrences.entrySet()) {
            p = classOccurr.getValue()/totalSize;
            entropy -= p*( (Math.log10(p))/(Math.log10(2)) );
        }
        return entropy;
    }
}
