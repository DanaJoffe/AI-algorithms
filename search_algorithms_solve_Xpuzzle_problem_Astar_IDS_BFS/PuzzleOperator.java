// dana joffe 312129240

/**
 * this enum class represents the operator that gets one puzzle state to another.
 */
public enum PuzzleOperator {
    U(1), D(2), L(3), R(4);

    private final int value;

    private PuzzleOperator(int value) {
        this.value = value;
    }

    /**
     * @return the int value of the operation.
     */
    public int getValue() {
        return value;
    }
}