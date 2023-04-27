package component;

import com.google.gson.JsonSyntaxException;
import util.*;

import java.io.IOException;
import java.net.URISyntaxException;

public class Garden { // TODO: test

    private final int width; // x
    private final int height; // y
    private final Lawnmower lawnmower;
    private final Square[][] squares;
    private Stack<Direction> directionStack;
    private Square currentSquare;
    private int squaresDone = 0;
    private final ApiHandler apiHandler;
    private Weather weather;

    public Garden(int width, int height) {
        this.width = width;
        this.height = height;

        lawnmower = new Lawnmower();

        squares = new Square[height][width];
        for (int i = 0; i < height; i++) {
            squares[i] = new Square[width];
            for (int j = 0; j < squares[i].length; j++) {
                squares[i][j] = new Square();
            }
        }

        directionStack = new Stack<>();
        currentSquare = squares[0][0];
        apiHandler = new ApiHandler();
    }

    private boolean isBatteryEnough() {
        // returning to charging station + mowing current square + moving to next square
        double mowConsumption = lawnmower.getMowConsumption();
        double moveConsumption = lawnmower.getMoveConsumption();
        double totalFuelNeeded =
                (directionStack.getSize() * moveConsumption) + (currentSquare.getGrassLength() * mowConsumption) + moveConsumption;
        double currentBatteryCharge = lawnmower.getBatteryCharge();

        System.out.println("Fuel needed: " + totalFuelNeeded); // DEBUG
        return currentBatteryCharge >= totalFuelNeeded;
    }

    private void printWeatherInfo() {
        try {
            weather = apiHandler.getWeatherInfo();
        } catch (JsonSyntaxException | InterruptedException | URISyntaxException | IOException e) {
            System.out.println("Error when requesting weather information.");
        }
        System.out.printf(
                "Location: %s%nTime: %s%nCondition: %s%nCloudy: %s%n",
                weather.locationName(),
                weather.isDay() ? "day" : "night",
                weather.currentCondition().toLowerCase(),
                weather.currentCloud() > 25 ? "yes" : "no"
        );
    }

    private void work() {
        if (currentSquare.getGrassState() == GrassState.UNCUT) {
            long workTime = 1000;
            lawnmower.mow(currentSquare);
            squaresDone++;
            try {
                Thread.sleep(workTime);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted in Garden.work().");
            }
        }
    }

    private boolean isDone() {
        return (width * height) == squaresDone;
    }

    private Direction determineNextDirection() {
        // does the current square have:
        //    1. an upper neighbor that is unvisited? -> go up
        //    2. a lower neighbor that is unvisited? -> go down
        //    3. a right neighbor that is unvisited? -> go right
        //    4. a left neighbor that is unvisited? -> go left
        int currentX = lawnmower.getCurrentPos().getX();
        int currentY = lawnmower.getCurrentPos().getY();
        Direction nextDir = null;

        // up
        if ( (currentY < height - 1)
                && (squares[currentY + 1][currentX].getGrassState().equals(GrassState.UNCUT)) ) {
            nextDir = Direction.UP;
        }
        // down
        else if ( (currentY > 0)
                && (squares[currentY - 1][currentX].getGrassState().equals(GrassState.UNCUT)) ) {
            nextDir = Direction.DOWN;
        }
        // right
        else if ( (currentX < width - 1)
                && (squares[currentY][currentX + 1].getGrassState().equals(GrassState.UNCUT)) ) {
            nextDir = Direction.RIGHT;
        }
        // left
        else if ( (currentX > 0)
                && (squares[currentY][currentX - 1].getGrassState().equals(GrassState.UNCUT)) ) {
            nextDir = Direction.LEFT;
        }
        return nextDir;
    }

    private void move(Direction direction) {
        lawnmower.move(direction);
        Position newPos = lawnmower.getCurrentPos();
        currentSquare = squares[newPos.getY()][newPos.getX()];
    }

    public void run() {
        // 0. print weather info
        // 1. print garden status
        // 2. print lawnmower status
        // 3. determine if fuel is enough for next cut and returning
        // 4. work on current square
        // 5. check if garden is done
        // 6. determine next direction
        // 7. move to next direction, set current square
        printWeatherInfo();
        if (weather.currentCloud() < 25 && weather.isDay()) {
            lawnmower.enableSolarPanel();
            System.out.println("Solar panel is enabled.\n");
        } else {
            System.out.println("Solar panel is disabled.\n");
        }
        while (true) {
            System.out.println(this);
            lawnmower.printStatus();
            if (isBatteryEnough()) {
                work();
                if (isDone()) {
                    System.out.println("\n=== DONE. ===");
                    break;
                }
                Direction nextDirection = determineNextDirection();
                if (nextDirection != null) {
                    move(nextDirection);
                    directionStack.push(Direction.getOppositeDirection(nextDirection));
                }
            } else {
                System.out.println("Not enough charge, heading back to charging station.");
                guideBack(); // to charging station
                lawnmower.rechargeBattery();
                guideBack(); // to next square that needs to be worked on
            }
        }
    }

    private void guideBack() {
        Stack<Direction> newStack = new Stack<>();
        while (directionStack.getSize() != 0) {
            Direction poppedDirection = directionStack.pop();
            move(poppedDirection);
            newStack.push(Direction.getOppositeDirection(poppedDirection));
        }
        directionStack = newStack;
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = squares.length - 1; i >= 0; i--) {
            for (int j = 0; j < squares[i].length; j++) {
                if (squares[i][j] != currentSquare) {
                    sb.append(squares[i][j]);
                } else {
                    sb.append(lawnmower);
                }
                sb.append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
