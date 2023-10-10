package elevator;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.PriorityQueue;

@Data
@NoArgsConstructor
public class Elevator {
    String currentDirection = "STOPPED";
    int currentLiftFloor = 1;
    PriorityQueue<Integer> downFloors = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> upwardFloors = new PriorityQueue<>();

    public void callLift(User user, String requestType) throws InterruptedException {
        switch (currentDirection) {
            case "DOWN":
                if (requestType.equals("EXTERNAL")) {
                    String direction = user.getExtReq().getDirection();
                    int currentFloorUser = user.getCurrentFloor();
                    if (direction.equals("UP")) {
                        upwardFloors.add(currentFloorUser);
                    } else {
                        if (currentFloorUser > currentLiftFloor)
                            upwardFloors.add(currentFloorUser);
                        else
                            downFloors.add(currentFloorUser);
                    }
                } else {
                    int destFloorUser = user.getIntReq().getFloorNumber();
                    int floorDifference = currentLiftFloor - destFloorUser;
                    if (floorDifference > 0) {
                        downFloors.add(destFloorUser);
                    } else {
                        upwardFloors.add(destFloorUser);
                    }
                }
                break;
            case "UP":
                if (requestType.equals("EXTERNAL")) {
                    String direction = user.getExtReq().getDirection();
                    int currentFloorUser = user.getCurrentFloor();
                    if (direction.equals("DOWN")) {
                        downFloors.add(currentFloorUser);
                    } else {
                        if (currentFloorUser < currentLiftFloor)
                            downFloors.add(currentFloorUser);
                        else
                            upwardFloors.add(currentFloorUser);
                    }
                } else {
                    int destFloorUser = user.getIntReq().getFloorNumber();
                    int floorDifference = destFloorUser - currentLiftFloor;
                    if (floorDifference > 0) {
                        upwardFloors.add(destFloorUser);
                    } else {
                        downFloors.add(destFloorUser);
                    }
                }
                break;
            case "STOPPED":
                if (requestType.equals("EXTERNAL")) {
                    int currentFloorUser = user.getCurrentFloor();
                    if (currentFloorUser > currentLiftFloor) {
                        currentDirection = "UP";
                        upwardFloors.add(currentFloorUser);
                    } else if (currentFloorUser < currentLiftFloor) {
                        currentDirection = "DOWN";
                        downFloors.add(currentFloorUser);
                    } else {
                        currentDirection = "STOPPED";
                    }
                } else {
                    int destFloorUser = user.getIntReq().getFloorNumber();
                    if (destFloorUser > currentLiftFloor) {
                        upwardFloors.add(destFloorUser);
                    } else if (destFloorUser < currentLiftFloor) {
                        downFloors.add(destFloorUser);
                    } else {
                        currentDirection = "STOPPED";
                    }
                }
                break;
        }
        moveLift();
    }

    public void moveLift() throws InterruptedException {
        switch (currentDirection) {
            case "STOPPED":
                return;
            case "UP":
                while (!upwardFloors.isEmpty()) {
                    int nextFloor = upwardFloors.poll();
                    System.out.println("Lift is moving up to floor " + nextFloor);
                    currentLiftFloor = nextFloor;
                    Thread.sleep(1000);
                }
                if (downFloors.isEmpty()) {
                    currentDirection = "STOPPED";
                } else {
                    currentDirection = "DOWN";
                }
                break;
            case "DOWN":
                while (!downFloors.isEmpty()) {
                    int nextFloor = downFloors.poll();
                    System.out.println("Lift is moving down to floor " + nextFloor);
                    currentLiftFloor = nextFloor;
                    Thread.sleep(1000);
                }
                if (upwardFloors.isEmpty()) {
                    currentDirection = "STOPPED";
                } else {
                    currentDirection = "UP";
                }
                break;
        }
    }
}
