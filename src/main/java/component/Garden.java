package component;

import util.*;

public class Garden { // TODO: test

    private final int width; // x
    private final int height; // y
    private final Lawnmower lawnmower;
    private final Square[][] squares;
    private final Stack<Square> visited;
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

        visited = new Stack<>();
        currentSquare = squares[0][0];
        apiHandler = new ApiHandler();
    }

//    public boolean isFuelEnough() {
//
//    }

    public void printWeatherInfo() {
        weather = apiHandler.getWeatherInfo();
        System.out.printf(
                "Location: %s%nCondition: %s%nCloudy: %s%n",
                weather.locationName(),
                weather.currentCondition(),
                weather.currentCloud() > 25 ? "Yes" : "No"
        );
    }

    private void work() throws InterruptedException {
        if (currentSquare.getGrassState() == GrassState.UNCUT) {
            long workTime = 1000;
            lawnmower.mow(currentSquare);
            squaresDone++;
            Thread.sleep(workTime);
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

    private void move(Direction d) {
        lawnmower.move(d);
        Position newPos = lawnmower.getCurrentPos();
        currentSquare = squares[newPos.getY()][newPos.getX()];
        visited.push(currentSquare);
    }

    public void run() {
        // 1. print garden status
        // 2. print lawnmower status
        // 3. determine if fuel is enough for next cut and returning
        // 4. work on current square
        // 5. check if garden is done
        // 6. determine next direction
        // 7. move to next direction, set current square
        while (true) {
            System.out.println(this);
            lawnmower.printStatus();
            try {
                work();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isDone()) {
                System.out.println("\n=== DONE. ===");
                break;
            }
            Direction nextDirection = determineNextDirection();
            if (nextDirection != null) {
                move(nextDirection);
            }
        }
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
