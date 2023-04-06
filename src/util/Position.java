package util;

public class Position {

    private int x; // width
    private int y; // height
    private final int X_MAX;
    private final int Y_MAX;

    public Position() {
        this(5, 5);
    }

    public Position(int X_MAX, int Y_MAX) {
        x = 0;
        y = 0;
        this.X_MAX = X_MAX - 1;
        this.Y_MAX = Y_MAX - 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean setX(int x) {
        if (x > X_MAX || x < 0) {
            System.out.println("Cannot place out of bounds");
            return false;
        }
        this.x = x;
        System.out.println("Successful replacement.");
        return true;
    }

    public boolean setY(int y) {
        if (y > Y_MAX || y < 0) {
            System.out.println("Cannot place out of bounds.");
            return false;
        }
        this.y = y;
        System.out.println("Successful replacement.");
        return true;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean move(Direction d) {
        boolean success = false;
        return switch (d) {
            case UP -> {
                if (y < Y_MAX) {
                    y += 1;
                    success = true;
                    System.out.println("Moving up.");
                } else {
                    System.out.println("Cannot move up from current position.");
                }
                yield success;
            }

            case DOWN -> {
                if (y > 0) {
                    y -= 1;
                    success = true;
                    System.out.println("Moving down.");
                } else {
                    System.out.println("Cannot move down from current position.");
                }
                yield success;
            }

            case LEFT -> {
                if (x > 0) {
                    x -= 1;
                    success = true;
                    System.out.println("Moving left.");
                } else {
                    System.out.println("Cannot move left from current position.");
                }
                yield success;
            }

            case RIGHT -> {
                if (x < X_MAX) {
                    x += 1;
                    success = true;
                    System.out.println("Moving right.");
                } else {
                    System.out.println("Cannot move right from current position.");
                }
                yield success;
            }
        };
    }
}
