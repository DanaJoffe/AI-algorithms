// dana joffe 312129240

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IExamples extends Iterable<Example>{
    boolean isEmpty();
    Set<String> getAllExistingUniqueClasses();
    IExamples filter(ExamplesFilter ef);
    List<String> getAttributesTitles();
    int size();
    Map<String, Long> getAllClassOccurrAmount();
    List<IAttribute> getAttributes();
}
