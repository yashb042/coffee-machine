package org.example.machines;

import org.example.food_items.Coffee;
import org.example.food_items.FoodItem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CoffeeMachine implements Machine {

    public CoffeeMachine(String name) {
        this.name = name;
    }

    private String name;
    private boolean used = false;

    @Override
    public String getMachineType() {
        return "CoffeeMachine";
    }

    @Override
    public FoodItem prepareFood() {
        System.out.println("Using coffee machine " + name);
        used = true;
        FoodItem coffee = new Coffee();
        boolean canBePrepared = ingRepo.getIngForFood(coffee.requiredItems());

        if (!canBePrepared) {
            System.out.println("Not enough Ing");
            used = false;
            return null;
        }

//        System.out.println("Preparing coffee - " + coffee.prepareTime());
        try {
            Thread.sleep(coffee.prepareTime() * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Coffee Prepared");
        used = false;
        return coffee;


//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        executorService.submit(()-> waitWhileFoodPrepared(coffee.prepareTime()));
    }

    @Override
    public void waitWhileFoodPrepared(int time) {
        try {
            Thread.sleep(time * 1000);
            System.out.println("Coffee Prepared");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean inUse() {
        return used;
    }
}
