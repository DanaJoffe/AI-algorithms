// dana joffe 312129240

/**
 * defines a leaf on the DecisionTree.
 */
public class ClassificationNode implements DecisionTree {
    private String classification;

    /**
     * constructor.
     * @param classification - the classification of the path from the head of the tree to this leaf.
     */
    public ClassificationNode(String classification) {
        super();
        this.classification = classification;
    }

    /**
     * @return the classification of the path from the head of the tree to this leaf.
     */
    public String getHeadName() {
        return this.classification;
    }

    /**
     * @param prefix - the prefix before this node's information.
     * @return this tree as a string representation
     */
    public String toString(String prefix) {
        return this.classification;
    }

    /**
     * not possible. this method shouldn't be called.
     * @param value - a possible value for a attribute node.
     * @param subtree - the subtree that the given value leads to.
     * @throws Exception
     */
    public void addSubTree(String value, DecisionTree subtree) throws Exception {
        throw new Exception("can't add subtree to leaf");
    }

    /**
     * @return true, this is a leaf..
     */
    public boolean isLeaf() {
        return true;
    }

    /**
     * a leaf doesn't have subtrees.
     * @param value - a value of an attribute node.
     * @return null.
     */
    public DecisionTree getSubTree(String value) {
        return null;
    }
}
