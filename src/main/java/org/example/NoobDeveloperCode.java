package org.example;

public class NoobDeveloperCode {

    public void f1() {
        long startTime = System.currentTimeMillis();

        // This is the code that we want to measure
        System.out.println("I am doing some basic tasks");
        // The code ends here

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken to do basic tasks: " + (endTime - startTime));
    }

    public void f2() {
        long startTime = System.currentTimeMillis();

        // This is the code that we want to measure
        System.out.println("I am doing some complex tasks");

        // The code ends here

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken to do complex tasks: " + (endTime - startTime));
    }
}
