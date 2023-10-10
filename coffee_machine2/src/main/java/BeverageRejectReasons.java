import lombok.Data;

import java.util.HashMap;

public class BeverageRejectReasons extends HashMap<String, String> {
    public static final String NOT_PRESENT = "not present";
    public static final String NOT_SUFFECIENT_QUANTITY = "not having suffecient quantity";

    @Override
    public String toString() {
        String toString = "";
        if (!this.isEmpty()){
            int i = 0;
            for (Entry<String, String> entry : this.entrySet()){
                toString += ((i>0)?",":"") + entry.getKey() + " is " + entry.getValue();
                i++;
            }
        }
        return toString;
    }
}
