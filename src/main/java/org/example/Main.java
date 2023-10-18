package org.example;

import org.example.machines.MachineRepo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static Queue<String> orderQueue = new LinkedList<>();
    static MachineRepo machineRepo = new MachineRepo();

    public static void main(String[] args) throws InterruptedException {

        List<Operator> operatorList = new ArrayList<>();
        operatorList.add(new Operator("operator1"));
        operatorList.add(new Operator("operator2"));
        operatorList.add(new Operator("operator3"));

        placeOrders();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(() -> {
            try {
                keepTakingOrders(operatorList, executorService);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void keepTakingOrders(List<Operator> operatorList, ExecutorService executorService) throws InterruptedException {
        while (!orderQueue.isEmpty()) {
            boolean couldProcess = false;
            String food = orderQueue.poll();
            for (Operator operator : operatorList) {
                if (operator.canReceive()) {
                    executorService.submit(() -> {
                        try {
                            operator.prepareFood(food);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
//                    System.out.println("Sent for processing");
                    couldProcess = true;
                    break;
                }
            }
            if (!couldProcess)
                System.out.println("Operator not present");
        }
    }

    private static void placeOrders() throws InterruptedException {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer("Cust1"));
        customerList.add(new Customer("Cust2"));

        for (Customer customer : customerList) {
            customer.placeOrder("COFFEE");
        }
    }
}