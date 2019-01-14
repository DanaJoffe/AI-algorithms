// dana joffe 312129240

import java.util.List;
import java.util.Map;

/**
 * helper class that does all "find maximum" searchings.
 */
public class MaxFinder {
    /**
     * assume map is't empty, find the key with the highest value. if it doesn't exist and ignoreSecondMax is false-
     * throw an Exception.
     * @param map - a map of keys type K and Comparable values type Y.
     * @param ignoreSecondMax - if true, in case of two equal maximums, the first key is returned.
     *                        if false, an Exception is thrown.
     * @return the key with the highest value in map.
     * @throws Exception when ignoreSecondMax = false and there is no absolute maximum value.
     */
    public static <K, V extends Comparable<V>>  K findMaxByValueInMap(Map<K,V> map,
                                                                      boolean ignoreSecondMax) throws Exception {
        Map.Entry<K, V> maxEntry = null;
        Map.Entry<K, V> secondMaxEntry = null;
        // find the class with the maximum and second maximum value
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            } else if(secondMaxEntry == null || entry.getValue().compareTo(secondMaxEntry.getValue()) > 0) {
                secondMaxEntry = entry;
            }
        }
        // no need to check for equality in max
        if(ignoreSecondMax) {
            return maxEntry.getKey();
        }

        // there is an absolute maximum
        if (secondMaxEntry == null || !maxEntry.getValue().equals(secondMaxEntry.getValue())) {
            return maxEntry.getKey();
        }
        // there are two equal maximums
        throw new Exception("there are two equal maximums");
    }

    /**
     * assume map is't empty, find the key with the highest value. if it doesn't exist - throw an Exception.
     * @param map - a map of keys type K and Comparable values type Y.
     * @return the key with the highest value in map.
     * @throws Exception when there is no absolute maximum value.
     */
    public static <K, V extends Comparable<V>> K findMaxByValueInMap(Map<K,V> map) throws Exception {
        return MaxFinder.findMaxByValueInMap(map, false);
    }

    /**
     * assume pairs is't empty, find the key with the highest value. if it doesn't exist and ignoreSecondMax is false-
     * throw an Exception.
     * @param pairs - a list of pairs, each pair consists of keys type K and Comparable values type Y.
     * @param ignoreSecondMax - if true, in case of two equal maximums, the first key is returned.
     *                        if false, an Exception is thrown.
     * @return the key with the highest value in map.
     * @throws Exception when ignoreSecondMax = false and there is no absolute maximum value.
     */
    public static <K, V extends Comparable<V>> K findMaxByValueInPairList(List<Pair<K,V>> pairs,
                                                                          boolean ignoreSecondMax) throws Exception {
        Pair<K, V> maxEntry = null;
        Pair<K, V> secondMaxEntry = null;
        // find the attribute with the maximum IG
        for (Pair<K, V> entry : pairs) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            } else if(secondMaxEntry == null || entry.getValue().compareTo(secondMaxEntry.getValue()) > 0) {
                secondMaxEntry = entry;
            }
        }
        // no need to check for equality in max
        if(ignoreSecondMax) {
            return maxEntry.getKey();
        }
        // there is an absolute maximum
        if (secondMaxEntry == null || !maxEntry.getValue().equals(secondMaxEntry.getValue())) {
            return maxEntry.getKey();
        }
        // there are two equal maximums
        throw new Exception("there are two equal maximums");
    }

    /**
     * assume pairs is't empty, find the key with the highest value. if it doesn't exist - throw an Exception.
     * @param pairs - a list of pairs, each pair consists of keys type K and Comparable values type Y.
     * @return the key with the highest value in map.
     * @throws Exception when there is no absolute maximum value.
     */
    public static <K, V extends Comparable<V>> K findMaxByValueInPairList(List<Pair<K,V>> pairs) throws Exception {
        return MaxFinder.findMaxByValueInPairList(pairs, false);
    }
}