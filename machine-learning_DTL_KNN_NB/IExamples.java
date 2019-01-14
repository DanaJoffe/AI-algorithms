// dana joffe 312129240

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * data base for all examples (can be training set or test set).
 * example = Example object that describes some attribute's values and classification.
 */
public interface IExamples extends Iterable<Example>{
    /**
     * @return true if there are no examples in this data base.
     */
    boolean isEmpty();

    /**
     * @return a set of all classes that currently exist in this data base. each classification appears once.
     */
    Set<String> getAllExistingUniqueClasses();

    /**
     * @param ef - a filter that decides which examples to include in the new object, and which not.
     * @return a new IExamples object with all the examples that weren't filtered out.
     */
    IExamples filter(ExamplesFilter ef);

    /**
     * @return a list of all attributes' names.
     */
    List<String> getAttributesTitles();

    /**
     * @return the amount of examples in this data base.
     */
    int size();

    /**
     * @return a map of: classification => how many examples are classified with this classification.
     */
    Map<String, Long> getAllClassOccurrAmount();

    /**
     * @return a list of the attributes and their currently existing possible values.
     */
    List<IAttribute> getAttributesAndValues();
}
