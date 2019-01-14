// dana joffe 312129240

/**
 * describes a node and leaf in a decision tree.
 */
public interface DecisionTree {
    /**
     * add a subtree that is mapped to some value.
     * @param value - a possible value for a attribute node.
     * @param subtree - the subtree that the given value leads to.
     * @throws Exception if this method is called on a leaf.
     */
    void addSubTree(String value, DecisionTree subtree) throws Exception;

    /**
     * getter.
     * @return the attribute's name.
     */
    String getHeadName();

    /**
     * @param prefix - the prefix before this node's information.
     * @return this tree as a string representation
     */
    String toString(String prefix);

    /**
     * @return true if its a leaf, o.w. false.
     */
    boolean isLeaf();

    /**
     * @param value - a value of a attribute node.
     * @return the sub tree that is mapped by the given value.
     */
    DecisionTree getSubTree(String value);
}
