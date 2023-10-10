import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CoffeeMachineTest {
    @Test
    public void testFromTestData1() throws IOException, ExecutionException, InterruptedException {
        testFromData("test1");
    }

    @Test
    public void testFromTestData2() throws IOException, ExecutionException, InterruptedException {
        testFromData("test2");
    }

    private void testFromData(String folderName) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        File coffeeMachineFile = new File("src/test/java/data/" + folderName + "/coffee_machine.json");
        File beveragesFile = new File("src/test/java/data/" + folderName + "/beverages.json");

        CoffeeMachine coffeeMachine = objectMapper.readValue(coffeeMachineFile, CoffeeMachine.class);
        List<Beverage> beverages = Arrays.asList(objectMapper.readValue(beveragesFile, Beverage[].class));

        coffeeMachine.executeBeverageRequest(beverages);
    }
}
