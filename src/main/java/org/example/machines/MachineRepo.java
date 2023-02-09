package org.example.machines;

import java.util.ArrayList;
import java.util.List;

public class MachineRepo {

    public List<Machine> coffeeMachineList = new ArrayList<>();
    public List<Machine> burgerMachineList = new ArrayList<>();
    public List<Machine> sandwichMachineList = new ArrayList<>();

    public MachineRepo() {
        burgerMachineList.add(new BurgerMachine());
        burgerMachineList.add(new BurgerMachine());

        coffeeMachineList.add(new CoffeeMachine("coffeeMachine1"));
        coffeeMachineList.add(new CoffeeMachine("coffeeMachine2"));
        coffeeMachineList.add(new CoffeeMachine("coffeeMachine3"));

        sandwichMachineList.add(new SandwichMachine());
        sandwichMachineList.add(new SandwichMachine());
        sandwichMachineList.add(new SandwichMachine());

    }
}
