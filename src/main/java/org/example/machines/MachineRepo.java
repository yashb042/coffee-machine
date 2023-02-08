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

        coffeeMachineList.add(new CoffeeMachine());

        sandwichMachineList.add(new SandwichMachine());
        sandwichMachineList.add(new SandwichMachine());
        sandwichMachineList.add(new SandwichMachine());

    }
}
