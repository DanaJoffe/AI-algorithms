// dana joffe 312129240

import java.util.List;

public interface MachineLearningAlgo {
    String getAlgoName();
    List<Pair<Example, String>> classifyExamples(IExamples testCases);
}
