package org.example.food_items;

import org.example.ingredients.Ingredient;

import java.util.ArrayList;
import java.util.List;

public interface FoodItem {
    int prepareTime();

    ArrayList<Ingredient> requiredItems();
}
