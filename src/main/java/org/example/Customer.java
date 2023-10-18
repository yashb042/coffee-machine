package org.example;

public class Customer {

    String name;

    void placeOrder(String food) throws InterruptedException {
//        System.out.println("Placed order");
        Thread.sleep(1000);
        Main.orderQueue.add(food);
    }

    Customer(String name) {
        this.name = name;
    }
}
