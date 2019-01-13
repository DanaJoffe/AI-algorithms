// dana joffe 312129240

// this is an attribute node
public interface DecisionTree {
    void addSubTree(String value, DecisionTree subtree) throws Exception;
    String getHeadName();
    String toString(String prefix);
    boolean isLeaf();
    DecisionTree getSubTree(String value);
}
