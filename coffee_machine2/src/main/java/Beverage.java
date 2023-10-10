import lombok.Data;

import java.util.Map;

@Data
public class Beverage {
    String name;
    Map<String,Integer> ingredients;
    int timeToPrepareInSecond;
}
