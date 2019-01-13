// dana joffe 312129240

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * specifies all the possible values for an attribute.
 */
public class Attribute implements IAttribute{
    private String attributeName;
    private List<String> possibleValues;

    /**
     * constructor.
     * @param attName - attribute's name.
     * @param possibleValues - all the possible values this attribute can get.
     */
    public Attribute(String  attName, Set<String> possibleValues) {
        this.attributeName = attName;
        this.possibleValues = new ArrayList<String>(possibleValues);
    }

    /**
     * getter.
     * @return all the possible values this attribute can get.
     */
    public List<String> getAllPossibleValues() {
        return new ArrayList<String>(this.possibleValues);
    }

    /**
     * getter.
     * @return this attribute's name.
     */
    public String getName() {
        return this.attributeName;
    }

    /**
     * this attribute equals to o attribute if they have the same name.
     * @param o - other attribute.
     * @return true if attribute equals to o attribute, o.w. false.
     */
    public boolean equals(IAttribute o){
        return o.getName().equals(this.getName());
    }
}