// dana joffe 312129240

import java.util.List;

/**
 * algorithm that can classify Example, based on a train set.
 */
public interface MachineLearningAlgo {
    /**
     * @return the name of the algorithm.
     */
    String getAlgoName();

    /**
     * @param testCases - data base of test cases that need to be classified.
     * @return list of pairs: (test case, it's classification).
     */
    List<Pair<Example, String>> classifyExamples(IExamples testCases);
}
