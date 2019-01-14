// dana joffe 312129240

/**
 * function class that determines if an example should be filtered out.
 */
public interface ExamplesFilter {
    /**
     * @param e - an example object.
     * @return false if example e should be filtered out, o.w. true.
     */
    boolean include(Example e);
}
