package org.example.ingredients;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IngredientsRepo {

    //make this map as synchronised
    Map<Ingredient, Integer> ingredientQuantityMap = new HashMap<>();

    public void reduceIng(Ingredient ingredient) {
        Integer integer = ingredientQuantityMap.get(ingredient);
        ingredientQuantityMap.put(ingredient, integer - 1);
    }

    public boolean checkIng(Ingredient ingredient) {
        Integer integer = ingredientQuantityMap.get(ingredient);
        return integer != 0;
    }

    public boolean getIngForFood(ArrayList<Ingredient> ingredients) throws InterruptedException {
        Thread.sleep(1000);
        boolean isSufficient = ingredients.stream().anyMatch(this::checkIng);
        if (!isSufficient) {
            return false;
        }
        ingredients.forEach(this::reduceIng);
        return true;
    }

    public IngredientsRepo() {
        ingredientQuantityMap.put(Ingredient.MILK, 3);
        ingredientQuantityMap.put(Ingredient.BREAD, 2);
        ingredientQuantityMap.put(Ingredient.KETCHUP, 4);
        ingredientQuantityMap.put(Ingredient.TIKKI, 2);
    }
}
