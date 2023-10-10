package org.example;

import org.example.machines.Machine;

import java.util.List;

public class Operator {
    String name;

    boolean isFree = true;

    Operator(String name) {
        this.name = name;
    }

    void prepareFood(String food) {
        isFree = false;
        System.out.println("Operator started preparing - " + name);

        List<Machine> coffeeMachineList = Main.machineRepo.coffeeMachineList;
        Machine coffeeMachine = coffeeMachineList.stream().filter(machine -> !machine.inUse()).findFirst().orElse(null);
        if (coffeeMachine == null) {
            System.out.println("Dropping coffee order since machine not there");
            isFree = true;
            return;
        }
        coffeeMachine.prepareFood();


        isFree = true;
    }

    public boolean canReceive() {
        return isFree;
    }
}
