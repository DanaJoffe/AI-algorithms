// dana joffe 312129240

import java.util.*;

/**
 * represents a node in a decision tree that is an attribute.
 */
public class AttributeNode implements DecisionTree {
    private Map<String, DecisionTree> children;
    private String headName;

    /**
     * constructor.
     * @param headName - the attribute's name.
     */
    public AttributeNode(String headName) {
        this.children = new HashMap<String, DecisionTree>();
        this.headName = headName;
    }

    /**
     * add a subtree that is mapped to some value.
     * @param value - a possible value for this attribute.
     * @param subtree - the subtree that the given value leads to.
     */
    public void addSubTree(String value, DecisionTree subtree) {
        this.children.put(value, subtree);
    }

    /**
     * getter.
     * @return the attribute's name.
     */
    public String getHeadName() {
        return this.headName;
    }

    /**
     * @param prefix - the prefix before this node's information.
     * @return this tree as a string representation
     */
    public String toString(String prefix) {
        List<String> orderedChildren = new ArrayList<String>();
        orderedChildren.addAll(this.children.keySet());
        orderedChildren.sort(Comparator.naturalOrder());
        StringBuilder sb = new StringBuilder();
        for (String value: orderedChildren) {
            sb.append(prefix + "|" + this.getHeadName() + "=" + value);
            DecisionTree child = this.children.get(value);
            if (child.isLeaf()) {
                sb.append(":" + child.toString("") + "\n");
            } else {
                sb.append("\n" + child.toString(prefix + "\t"));
            }
        }
        return sb.toString();
    }

    /**
     * @return false, because Attribute Nodes are not leafs.
     */
    public boolean isLeaf() {
        return false;
    }

    /**
     * @param value - a value of this attribute.
     * @return the sub tree that is mapped by the given value.
     */
    public DecisionTree getSubTree(String value) {
        if (this.children.keySet().contains(value)) {
            return this.children.get(value);
        }
        return null;
    }
}