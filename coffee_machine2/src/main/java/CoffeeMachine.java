import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Data
public class CoffeeMachine {
    int numberOfOutlets;
    Map<String, Integer> inventories;

    // Takes the list of Beverages and runs ExecutorService
    public void executeBeverageRequest(List<Beverage> beverages) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(this.numberOfOutlets);
        List<BeveragePrepareTask> beveragePrepareTasks = new ArrayList<BeveragePrepareTask>();
        for (Beverage beverage: beverages){
            beveragePrepareTasks.add(new BeveragePrepareTask(this, beverage));
        }
        executorService.invokeAll(beveragePrepareTasks);
        executorService.shutdown();
    }

    // Checks is beverage possible
    private BeverageRejectReasons isBeveragePossible(Beverage beverage){
        BeverageRejectReasons beverageRejectReasons = new BeverageRejectReasons();
        for (Map.Entry<String,Integer> entryBeverage: beverage.ingredients.entrySet()){
            if (this.inventories.get(entryBeverage.getKey()) == null)
                beverageRejectReasons.put(entryBeverage.getKey(), BeverageRejectReasons.NOT_PRESENT);
            else if (this.inventories.get(entryBeverage.getKey()) < entryBeverage.getValue())
                beverageRejectReasons.put(entryBeverage.getKey(), BeverageRejectReasons.NOT_SUFFECIENT_QUANTITY);
        }
        return beverageRejectReasons;
    }

    // consume inventory for beverage
    public BeverageRejectReasons consumeInventoryForBeverage(Beverage beverage){
        BeverageRejectReasons beverageRejectReasons = isBeveragePossible(beverage);
        if (beverageRejectReasons.isEmpty()){
            for (Map.Entry<String,Integer> entryIngredientEntry: beverage.ingredients.entrySet()){
                this.inventories.put(entryIngredientEntry.getKey(),
                        this.inventories.get(entryIngredientEntry.getKey()) - entryIngredientEntry.getValue());
            }
        }
        return beverageRejectReasons;
    }

}

