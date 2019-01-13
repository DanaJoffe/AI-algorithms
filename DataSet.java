// dana joffe 312129240

import java.util.*;
import java.util.stream.Collectors;

public class DataSet implements IExamples {
    // keeps the order of examples
    private List<Example> examples;
    // keeps the order of attributes
    private List<String> attributesTitles;
    private String classificationName;

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

    private DataSet() {}

    public Set<String> getAllExistingUniqueClasses() {
        Set<String> classifications;
        classifications = this.examples.stream().map(example -> example.getClassification()).collect(Collectors.toSet());
        return classifications;
    }

    public boolean isEmpty() {
        return this.examples.isEmpty();
    }

    public IExamples filter(ExamplesFilter ef) {
        DataSet ds = new DataSet();
        ds.examples = this.examples.stream().filter(e -> ef.include(e)).collect(Collectors.toList());
        ds.classificationName = this.classificationName;
        ds.attributesTitles = this.attributesTitles;
        return ds;
    }

    public Iterator<Example> iterator() {
        return new ArrayList<Example>(examples).iterator();
    }

    public List<String> getAttributesTitles() {
        return this.attributesTitles;
    }

    public int size() {
        return this.examples.size();
    }


    public Map<String, Long> getAllClassOccurrAmount(){
        List<String> classifications = new ArrayList<String>();
        this.examples.forEach(example -> classifications.add(example.getClassification()));
        return classifications.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));
    }


    public List<IAttribute> getAttributes() {
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
