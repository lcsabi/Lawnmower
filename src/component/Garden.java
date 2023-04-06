package component;

import util.Direction;
import util.Stack;

public class Garden { // TODO: Test

    private final int height; // y
    private final int width; // x
    private final Lawnmower lawnmower;
    private final Square[][] squares;
    private Stack<Direction> directionStack;
    private boolean isDone = false;

    public Garden(int height, int width) {
        this.height = height;
        this.width = width;
        lawnmower = new Lawnmower(height, width);

        squares = new Square[height][width];
        for (int i = 0; i < height; i++) {
            squares[i] = new Square[width];
            for (int j = 0; j < width; j++) {
                squares[i][j] = new Square();
            }
        }

        directionStack = new Stack<>();
    }

    public Lawnmower getLawnmower() {
        return lawnmower;
    }

    public boolean isDone() {
        return isDone;
    }

    public void work() {

    }
}
