package org.example;

import org.example.food_items.FoodItem;
import org.example.ingredients.IngredientsRepo;
import org.example.machines.MachineRepo;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class Main {
    static Queue<String> orderQueue = new LinkedList<>();
    static MachineRepo machineRepo = new MachineRepo();

    public static void main(String[] args) throws InterruptedException {

        List<Operator> operatorList = new ArrayList<>();
        operatorList.add(new Operator("operator1"));
        operatorList.add(new Operator("operator2"));
        operatorList.add(new Operator("operator3"));

        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        executorService.submit(Main::placeOrders);

        placeOrders();


        executorService.submit(() -> keepTakingOrders(operatorList, executorService));


       // placeOrders();
    }

    private static void keepTakingOrders(List<Operator> operatorList, ExecutorService executorService) {
        while (!orderQueue.isEmpty()) {
            boolean couldProcess = false;
            String food = orderQueue.poll();
            for (Operator operator : operatorList) {
                if (operator.canReceive()) {
                    executorService.submit(() -> operator.prepareFood(food));
                    System.out.println("Sent for processing");
                    couldProcess = true;
                    break;
                }
            }
            if (!couldProcess)
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