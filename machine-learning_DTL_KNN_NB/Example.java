// dana joffe 312129240

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * contains information of one example: all of it's features and it's classification.
 */
public class Example {
    // maps the attribute's name to it's value
    private Map<String, String> features;
    private String classification;

    /**
     * constructor.
     * @param features - list of pairs: (attribute, value).
     * @param classification - the classification of the example.
     */
    public Example(List<Pair<String, String>> features, String classification) {
        this.classification = classification;
        this.features = new HashMap<String, String>();
        // go over pairs of attribute: value
        for(Pair<String, String> feature: features){
            this.features.put(feature.getKey(), feature.getValue());

        }
    }

    /**
     * @param attributeName - attribute's name.
     * @return the value of the given attribute for this example.
     */
    public String getValue(String attributeName) {
        return this.features.get(attributeName);
    }

    /**
     * getter.
     * @return the classification of the example.
     */
    public String getClassification() {
        return this.classification;
    }
}