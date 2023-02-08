package org.example;

import org.example.food_items.FoodItem;
import org.example.ingredients.IngredientsRepo;
import org.example.machines.MachineRepo;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static Queue<String> orderQueue = new LinkedList<>();
    static MachineRepo machineRepo = new MachineRepo();

    public static void main(String[] args) {

        List<Operator> operatorList = new ArrayList<>();
        operatorList.add(new Operator("1"));
        operatorList.add(new Operator("2"));
        operatorList.add(new Operator("3"));

        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        executorService.submit(Main::placeOrders);

        placeOrders();


        while (!orderQueue.isEmpty()) {
            String food = orderQueue.poll();
            for (Operator operator : operatorList) {
                if (operator.canReceive())
                    executorService.submit(() -> operator.prepareFood(food));
            }
            System.out.println("Operator not present");
//            orderQueue.add(food);
        }
    }

    private static void placeOrders() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer("Cust1"));
        customerList.add(new Customer("Cust2"));
        customerList.add(new Customer("Cust3"));
        customerList.add(new Customer("Cust4"));

        customerList.forEach(customer -> {
            customer.placeOrder("COFFEE");
        });
    }
}