// dana joffe 312129240

import java.util.List;

/**
 * specifies all the possible values for an attribute.
 */
public interface IAttribute{
    /**
     * getter.
     * @return all the possible values this attribute can get.
     */
    List<String> getAllPossibleValues();

    /**
     * getter.
     * @return this attribute's name.
     */
    String getName();

    /**
     * this attribute equals to o attribute if they have the same name.
     * @param o - other attribute.
     * @return true if attribute equals to o attribute, o.w. false.
     */
    boolean equals(IAttribute o);
}
