import java.util.concurrent.Callable;

class BeveragePrepareTask implements Callable<Object> {
    CoffeeMachine coffeeMachine;
    Beverage beverage;

    public BeveragePrepareTask(CoffeeMachine coffeeMachine, Beverage beverage) {
        this.coffeeMachine = coffeeMachine;
        this.beverage = beverage;
    }

    public Object call() throws Exception {
        BeverageRejectReasons beverageRejectReasons;
        synchronized (this.coffeeMachine){
            beverageRejectReasons = this.coffeeMachine.consumeInventoryForBeverage(beverage);
        }
        if(beverageRejectReasons.isEmpty()){
            try {
                System.out.println(this.beverage.getName() + " is prepared");
                Thread.sleep(beverage.getTimeToPrepareInSecond()*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println(this.beverage.getName() + " can not be prepared because " + beverageRejectReasons.toString());
        }

        return null;
    }
}