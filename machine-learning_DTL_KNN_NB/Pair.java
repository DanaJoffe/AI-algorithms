// dana joffe 312129240

/**
 * contains a pair of two classes.
 * @param <K> some class.
 * @param <V> some class.
 */
public class Pair<K,V> {
    /**
     * Key of this Pair.
     */
    private K key;

    /**
     * Value of this Pair.
     */
    private V value;

    /**
     * Gets the value for this pair.
     * @return value for this pair
     */
    public V getValue() { return value; }

    /**
     * Gets the key for this pair.
     * @return key for this pair
     */
    public K getKey() { return key; }


    /**
     * Creates a new pair
     * @param key The key for this pair
     * @param value The value to use for this pair
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * String representation of this Pair.
     * The default name/value delimiter '=' is always used.
     *  @return <code>String</code> representation of this <code>Pair</code>
     */
    @Override
    public String toString() {
        return key + "=" + value;
    }

    /**
     * Test this Pair for equality with another
     * Object.
     *
     * If the Object to be tested is not a
     * Pair or is  null, then this method
     * returns false.
     *
     * Two Pairs are considered equal if and only if
     * both the names and values are equal.
     *
     * @param o the Object to test for
     * equality with this Pair
     * @return true if the given Object is
     * equal to this Pair else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Pair) {
            Pair pair = (Pair) o;
            if (key != null ? !key.equals(pair.key) : pair.key != null) return false;
            if (value != null ? !value.equals(pair.value) : pair.value != null) return false;
            return true;
        }
        return false;
    }
}