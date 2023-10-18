package org.example.machines;

import org.example.food_items.FoodItem;
import org.example.ingredients.IngredientsRepo;

public interface Machine {

    String getMachineType();

    FoodItem prepareFood() throws InterruptedException;

    void waitWhileFoodPrepared(int time);

    boolean inUse();

    IngredientsRepo ingRepo = new IngredientsRepo();
}
