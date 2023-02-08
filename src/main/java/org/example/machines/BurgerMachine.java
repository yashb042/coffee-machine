package org.example.machines;

import org.example.food_items.FoodItem;

public class BurgerMachine implements Machine {


    @Override
    public String getMachineType() {
        return "BurgerMachine";
    }

    @Override
    public FoodItem prepareFood() {

        return null;
    }

    @Override
    public void waitWhileFoodPrepared(int time) {
        try {
            Thread.sleep(time * 1000);
            System.out.println("Burger Prepared");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean inUse() {
        return false;
    }
}
