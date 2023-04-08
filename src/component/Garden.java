package component;

import util.Direction;
import util.Stack;

public class Garden { // TODO: Test

    private final int height; // y
    private final int width; // x
    private final Lawnmower lawnmower;
    private final Square[][] squares;
    private final Stack<Direction> directionStack;
    private boolean isDone = false;

    public Garden(int width, int height) {
        this.width = width;
        this.height = height;
        lawnmower = new Lawnmower(width, height);

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return "";
    }
}
