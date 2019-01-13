// dana joffe 312129240

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example {
    // maps the attribute's name to it's value
    private Map<String, String> features;
    private String classification;

    public Example(List<Pair<String, String>> features, String classification) {
        this.classification = classification;
        this.features = new HashMap<String, String>();
        // go over pairs of attribute: value
        for(Pair<String, String> feature: features){
            this.features.put(feature.getKey(), feature.getValue());

        }
    }

    public String getValue(String attributeName) {
        return this.features.get(attributeName);
    }

    public String getClassification() {
        return this.classification;
    }
}
