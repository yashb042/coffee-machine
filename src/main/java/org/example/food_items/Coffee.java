package org.example.food_items;

import org.example.ingredients.Ingredient;

import java.util.ArrayList;

public class Coffee implements FoodItem {

    @Override
    public int prepareTime() {
        return 3;
    }

    @Override
    public ArrayList<Ingredient> requiredItems() {
        ArrayList<Ingredient> arr = new ArrayList<>();
        arr.add(Ingredient.MILK);
        return arr;
    }
}
