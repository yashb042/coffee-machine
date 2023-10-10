package elevator;

public class Main {
    public static void main(String[] args) throws InterruptedException {


        User user1 = new User(2, new IntReq(3), new ExtReq("UP"), "user1");
        User user2 = new User(4, new IntReq(2), new ExtReq("DOWN"), "user2");

        Elevator elevator = new Elevator();
        elevator.callLift(user1, "EXTERNAL");
        elevator.callLift(user2, "EXTERNAL");
    }
}
