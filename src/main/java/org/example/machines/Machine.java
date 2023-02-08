package org.example.machines;

import org.example.food_items.FoodItem;
import org.example.ingredients.IngredientsRepo;

public interface Machine {

    String getMachineType();

    FoodItem prepareFood();

    void waitWhileFoodPrepared(int time);

    boolean inUse();

    IngredientsRepo ingRepo = new IngredientsRepo();
}
