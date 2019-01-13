// dana joffe 312129240

import java.util.List;
import java.util.Map;

public class MaxFinder <K, V extends Comparable<V>> {

    // assumes map is't empty
    public K findMaxByValueInMap(Map<K,V> map, boolean ignoreSecondMax) throws Exception {
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

    public K findMaxByValueInMap(Map<K,V> map) throws Exception {
        return this.findMaxByValueInMap(map, false);
    }

    public K findMaxByValueInPairList(List<Pair<K,V>> pairs, boolean ignoreSecondMax) throws Exception {
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

    public K findMaxByValueInPairList(List<Pair<K,V>> pairs) throws Exception {
        return this.findMaxByValueInPairList(pairs, false);
    }

}
