// dana joffe 312129240

import java.util.*;
import java.util.stream.Collectors;

/**
 * data base for all examples (can be training set or test set).
 */
public class DataSet implements IExamples {
    // list, to keeps the order of examples
    private List<Example> examples;
    // list, to the order of attributes
    private List<String> attributesTitles;
    private String classificationName;

    /**
     * constructor.
     * @param data - all the examples and attributes' names.
     */
    public DataSet(String[][] data) {
        this.examples = new ArrayList<Example>();
        this.attributesTitles = new ArrayList<String>();
        int r= data.length;
        int c= data[0].length;

        // classification is the last column in data.
        this.classificationName = data[0][c-1];
        String[] attributes = Arrays.copyOfRange(data[0], 0, c-1);
        this.attributesTitles.addAll(Arrays.asList(attributes));

        List<Pair<String, String>> features;
        // go over examples
        for(int i = 1; i < r; i++) {
            features = new ArrayList<Pair<String, String>>();
            // go over features of one example
            for(int j = 0; j < c-1; j++) {
                features.add(new Pair<String, String>(attributes[j], data[i][j]));
            }
            examples.add(new Example(features, data[i][c-1]));
        }
    }

    /**
     * private constructor for making an internal copy.
     */
    private DataSet() {}

    /**
     * @return a set of all classes that currently exist in this dataSet. each classification appears once.
     */
    public Set<String> getAllExistingUniqueClasses() {
        Set<String> classifications;
        classifications = this.examples
                .stream()
                .map(example -> example.getClassification())
                .collect(Collectors.toSet());
        return classifications;
    }

    /**
     * @return true if there are no examples in this.examples.
     */
    public boolean isEmpty() {
        return this.examples.isEmpty();
    }

    /**
     * @param ef - a filter that decides which examples to include in the new DataSet, and which not.
     * @return a new IExamples object with all the examples that weren't filtered out.
     */
    public IExamples filter(ExamplesFilter ef) {
        DataSet ds = new DataSet();
        ds.examples = this.examples.stream().filter(e -> ef.include(e)).collect(Collectors.toList());
        ds.classificationName = this.classificationName;
        ds.attributesTitles = this.attributesTitles;
        return ds;
    }

    /**
     * @return an iterator object for the internal list of examples.
     */
    public Iterator<Example> iterator() {
        return new ArrayList<Example>(examples).iterator();
    }

    /**
     * @return a list of all attributes' names.
     */
    public List<String> getAttributesTitles() {
        return this.attributesTitles;
    }

    /**
     * @return the amount of examples in this DataSet.
     */
    public int size() {
        return this.examples.size();
    }

    /**
     * @return a map of: classification => how many examples are classified with this classification.
     */
    public Map<String, Long> getAllClassOccurrAmount(){
        List<String> classifications = new ArrayList<String>();
        this.examples.forEach(example -> classifications.add(example.getClassification()));
        return classifications.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));
    }

    /**
     * @return a list of the attributes and their currently existing possible values.
     */
    public List<IAttribute> getAttributesAndValues() {
        Map<String, Set<String>> attributeToValues = new HashMap<String, Set<String>>();

        List<String> attributesTitles = this.getAttributesTitles();
        List<Pair<String, Set<String>>> attToValues = attributesTitles
                .stream()
                .map(s -> new Pair<String, Set<String>>(s, new HashSet<String>()))
                .collect(Collectors.toList());

        for (Example example: this) {
            for(String attribute: attributesTitles) {
                if(attributeToValues.containsKey(attribute)) {
                    attributeToValues.get(attribute).add(example.getValue(attribute));
                } else {
                    Set<String> values = new HashSet<String>();
                    values.add(example.getValue(attribute));
                    attributeToValues.put(attribute, values);
                }
            }
        }
        attToValues.forEach(stringSetPair ->
                stringSetPair.getValue().addAll(attributeToValues.get(stringSetPair.getKey())));

        return attToValues.stream().
                map(pair -> new Attribute(pair.getKey(), pair.getValue())).collect(Collectors.toList());
    }
}
