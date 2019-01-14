// dana joffe 312129240

/**
 *  a helper class to organise & analyse 'the X puzzle' input.
 */
public class InputFile {
    private int algoNumber;
    private int puzzleSize;
    private int[][] initialState;

    /**
     * constructor.
     * @param algoNumber - 1 for IDS, 2 for BFS, 3 for A*.
     * @param puzzleSize - the puzzle n size: nXn
     * @param initialState - integers matrix representation of initial state.
     */
    public InputFile(int algoNumber, int puzzleSize, int[][] initialState) {
        this.algoNumber = algoNumber;
        this.puzzleSize = puzzleSize;
        this.initialState = initialState;
    }

    /**
     * Getter.
     * @return the algorithm number.
     */
    public int getAlgoNumber() {
        return this.algoNumber;
    }

    /**
     * Getter.
     * @return the puzzle size.
     */
    public int getPuzzleSize() {
        return this.puzzleSize;
    }

    /**
     * Getter.
     * @return the initial state.
     */
    public int[][] getInitialState() {
        return this.initialState;
    }
}