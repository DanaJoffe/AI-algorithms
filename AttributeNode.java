// dana joffe 312129240

import java.util.*;

public class AttributeNode implements DecisionTree {

    private Map<String, DecisionTree> children;
    private String headName;

    public AttributeNode(String headName) {
        this.children = new HashMap<String, DecisionTree>();
        this.headName = headName;
    }

    public AttributeNode(){}


    public void addSubTree(String value, DecisionTree subtree) throws Exception {
        this.children.put(value, subtree);
    }

    public String getHeadName() {
        return this.headName;
    }

    public String toString(String prefix) {
        List<String> orderedChildren = new ArrayList<String>();
        orderedChildren.addAll(this.children.keySet());
        orderedChildren.sort(Comparator.naturalOrder());
        StringBuilder sb = new StringBuilder();
        for (String value: orderedChildren) {
            sb.append(prefix + "|" + this.getHeadName() + "=" + value);//valueToChild.getKey());
            DecisionTree child = this.children.get(value);//valueToChild.getValue();
            if (child.isLeaf()) {
                sb.append(":" + child.toString("") + "\n");
            } else {
                sb.append("\n" + child.toString(prefix + "\t"));
            }
        }
        return sb.toString();
    }

    public boolean isLeaf() {
        return false;
    }

    public DecisionTree getSubTree(String value) {
        if (this.children.keySet().contains(value)) {
            return this.children.get(value);
        }
        return null;
    }
}
