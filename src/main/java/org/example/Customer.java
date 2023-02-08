package org.example;

import org.example.food_items.FoodItem;

import java.util.List;

public class Customer {

    String name;

    void placeOrder(String food) {
        System.out.println("Placed order");
        Main.orderQueue.add(food);
    }

    Customer(String name) {
        this.name = name;
    }
}
