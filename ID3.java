// dana joffe 312129240

import java.util.*;

public class ID3 implements MachineLearningAlgo{

    private DecisionTree decisionTree;
    public ID3() {}

    public String getAlgoName() {
        return "DT";
    }

    public List<Pair<Example, String>> classifyExamples(IExamples testCases){
        if (this.decisionTree == null) {
            return null;
        }
        return this.classifyExamples(testCases, this.decisionTree);
    }

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

    public DecisionTree MODE(IExamples examples) {
        Map<String, Long> occurrences = examples.getAllClassOccurrAmount();
        String classification;
        try {
            classification = new MaxFinder<String, Long>().findMaxByValueInMap(occurrences);
        } catch (Exception e) {
            // return the positive
            classification = Ex2Guidelines.getPositiveClass(occurrences.keySet());
        }
        return new ClassificationNode(classification);
    }

    private List<Pair<Example, String>> classifyExamples(IExamples testCases, DecisionTree decisionTree) {
        List<Pair<Example, String>> classifications = new ArrayList<Pair<Example, String>>();
        testCases.forEach(testCase ->
                classifications.add(new Pair<>(testCase, this.classifyTestCase(testCase, decisionTree))));
        return classifications;
    }

    private String classifyTestCase(Example testCase, DecisionTree decisionTree) {
        DecisionTree node = decisionTree;
        DecisionTree classification = decisionTree;
        while (node!= null) {
            classification = node;
            node = node.getSubTree(testCase.getValue(node.getHeadName()));
        }
        return classification.getHeadName();
    }

    private IAttribute ChooseAttribute(IExamples examples, List<IAttribute> attributes) {
        // maps every attribute to it's gain
        List<Pair<IAttribute, Float>> allIG = new ArrayList<Pair<IAttribute, Float>>();
        attributes.forEach(attribute -> allIG.
                add(new Pair<IAttribute, Float>(attribute, this.informationGain(examples, attribute))));
        // find the attribute with the maximum IG
        try {
            return new MaxFinder<IAttribute, Float>().findMaxByValueInPairList(allIG, true);
        } catch (Exception e) { return null; }
    }

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
