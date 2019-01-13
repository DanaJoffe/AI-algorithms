// dana joffe 312129240

// defines a leaf on the DecisionTree
public class ClassificationNode implements DecisionTree {
    private String classification;

    public ClassificationNode(String classification) {
        super();
        this.classification = classification;
    }

    public String getHeadName() {
        return this.classification;
    }

    public String toString(String prefix) {
        return this.classification;
    }

    public void addSubTree(String value, DecisionTree subtree) throws Exception {
        throw new Exception("can't add subtree to leaf");
    }

    public boolean isLeaf() {
        return true;
    }

    public DecisionTree getSubTree(String value) {
        return null;
    }
}
