// dana joffe 312129240

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * this class represents a specific puzzleState logic of a defined search problem.
 * this is the logic of a puzzle puzzleState in 'the x puzzle' problem.
 */
public class PuzzleGeneralState implements GeneralState <PuzzleGeneralState> {
    private int[][] puzzleState;
    private PuzzleGeneralState parent;
    private PuzzleOperator operator;

    private int zero_row_indx;
    private int zero_col_indx;
    private int puzzleSize;

    /**
     * constructor.
     * @param puzzleRep - the puzzle representation in this puzzleState.
     * @param parent_ - the puzzleState from which this puzzleState was made.
     * @param op - puzzle operator; the "move" that turned parent puzzleState to this puzzleState.
     */
    public PuzzleGeneralState(int[][] puzzleRep, PuzzleGeneralState parent_, PuzzleOperator op) {
        this.puzzleState = puzzleRep;
        this.parent = parent_;
        this.operator = op;
        this.puzzleSize = puzzleRep.length;

        for (int i = 0; i < this.puzzleSize; ++i) {
            for (int j = 0; j < this.puzzleSize; j++) {
                if (puzzleRep[i][j] == 0) {
                    this.zero_row_indx = i;
                    this.zero_col_indx = j;
                }
            }
        }
    }

    /**
     * constructor.
     * @param puzzleRep - the puzzle representation in this puzzleState.
     * @param parent_ - the puzzleState from which this puzzleState was made.
     * @param op - puzzle operator; the "move" that turned parent puzzleState to this puzzleState.
     * @param zero_row_indx - row index of the empty square in puzzleState
     * @param zero_col_indx - column index of the empty square in puzzleState
     */
    public PuzzleGeneralState(int[][] puzzleRep, PuzzleGeneralState parent_, PuzzleOperator op, int zero_row_indx , int zero_col_indx) {
        this.puzzleState = puzzleRep;
        this.parent = parent_;
        this.operator = op;
        this.puzzleSize = puzzleRep.length;

        this.zero_row_indx = zero_row_indx ;
        this.zero_col_indx = zero_col_indx;
    }

    /**
     * constructor.
     * @param puzzleRep - the puzzle representation in this puzzleState.
     */
    public PuzzleGeneralState(int[][] puzzleRep) {
        this(puzzleRep, null, null);
    }

    /**
     * Getter.
     * @return the puzzle size.
     */
    public int getPuzzleSize() {
        return this.puzzleSize;
    }

    /**
     * @param num - a number from 1 to this.puzzleSize-1
     * @return [row index, column index] of num in this.puzzleState.
     */
    public int[] getIndexesOf(int num) {
        int[] indexes = new int[2];
        for (int i=0; i<this.puzzleSize; i++) {
            for (int j = 0; j<this.puzzleSize; j++) {
                if (this.puzzleState[i][j] == num) {
                    indexes[0] = i;
                    indexes[1] = j;
                    return indexes;
                }
            }
        }
        return null;
    }

    /**
     * Getter.
     * @return the puzzle operator; the "move" that turned parent puzzleState to this puzzleState.
     */
    public PuzzleOperator getOperator() {
        return this.operator;
    }

    /**
     * calculates the following possible puzzle states for all operators available from: U, D, L, R.
     * @return a list of the successors = suns of current puzzleState = possible puzzle states.
     */
    public List<PuzzleGeneralState > getSuccessors() {
        ArrayList<PuzzleGeneralState> successors = new ArrayList<PuzzleGeneralState>();
        if (this.zero_row_indx != this.puzzleSize-1) { successors.add(this.oneSuccessor(PuzzleOperator.U)); }
        if (this.zero_row_indx != 0) { successors.add(this.oneSuccessor(PuzzleOperator.D)); }
        if (this.zero_col_indx != this.puzzleSize-1) { successors.add(this.oneSuccessor(PuzzleOperator.L)); }
        if (this.zero_col_indx != 0) { successors.add(this.oneSuccessor(PuzzleOperator.R)); }
        return successors;
    }

    /**
     * @param op - an operator {U, D, L, R}.
     * @return the next PuzzleGeneralState that will be created after exert op on this puzzleState.
     */
    private PuzzleGeneralState oneSuccessor(PuzzleOperator op) {
        int i = this.zero_row_indx, j = this.zero_col_indx, temp;
        int[][] newState = this.deepCopy(this.puzzleState);
        switch (op) {
            case U:
                i ++;
                temp = newState[i][j];
                newState[i][j] = 0;
                newState[this.zero_row_indx][this.zero_col_indx] = temp;
                break;
            case D:
                i--;
                temp = newState[i][j];
                newState[i][j] = 0;
                newState[this.zero_row_indx][this.zero_col_indx] = temp;
                break;
            case L:
                j++;
                temp = newState[i][j];
                newState[i][j] = 0;
                newState[this.zero_row_indx][this.zero_col_indx] = temp;
                break;
            case R:
                j--;
                temp = newState[i][j];
                newState[i][j] = 0;
                newState[this.zero_row_indx][this.zero_col_indx] = temp;
                break;
        }
        return new PuzzleGeneralState(newState, this, op, i, j);
    }

    /**
     * @param original - integer matrix.
     * @return a deep copy of original.
     */
    private int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }
        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }

    /**
     * @return A series of operations that will bring the initial puzzleState to a current puzzleState.
     */
    public String getPath() {
        if(this.parent == null) {
            return "";
        }
        return this.parent.getPath() + this.operator;
    }

    /**
     * @return a string representation of this.puzzleSize.
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < this.puzzleSize; ++i) {
            for (int j = 0; j < this.puzzleSize; j++) {
                out.append(this.puzzleState[i][j]).append(" ");
            }
            out.append("\n");
        }
        return out.toString();
    }

    /**
     * @param o - a PuzzleGeneralState object.
     * @return true if the arguments are equal to each other and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }
        /* Check if o is an instance of PuzzleState or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof PuzzleGeneralState)) {
            return false;
        }
        PuzzleGeneralState puzzleState = (PuzzleGeneralState) o;
        // Compare the states
        return Arrays.deepEquals(puzzleState.puzzleState, this.puzzleState);
    }

    /**
     * determines the Order ratio between this state and a given state p by the operator value: U < D < L < R.
     * @param p - a PuzzleGeneralState.
     * @return a negative integer, zero, or a positive integer as this is less than,
     * equal to, or greater than t.
     */
    public int compareTo(PuzzleGeneralState  p) {
        return this.operator.getValue() - p.getOperator().getValue();
    }

    /**
     * Getter.
     * @return this parent.
     */
    public PuzzleGeneralState getParent() {
        return this.parent;
    }
}