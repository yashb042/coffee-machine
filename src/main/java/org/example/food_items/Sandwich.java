package org.example.food_items;

import org.example.ingredients.Ingredient;

import java.util.ArrayList;

public class Sandwich implements FoodItem {

    @Override
    public int prepareTime() {
        return 30;
    }

    @Override
    public ArrayList<Ingredient> requiredItems() {
        ArrayList<Ingredient> arr = new ArrayList<>();
        arr.add(Ingredient.BREAD);
        arr.add(Ingredient.KETCHUP);
        return arr;
    }
}
