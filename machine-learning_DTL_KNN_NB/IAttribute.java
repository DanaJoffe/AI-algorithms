// dana joffe 312129240

import java.util.List;

public interface IAttribute{
    List<String> getAllPossibleValues();
    String getName();
    boolean equals(IAttribute o);

}
