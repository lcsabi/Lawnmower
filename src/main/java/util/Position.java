package util;

import java.util.Objects;

public class Position {

    private int y; // height
    private int x; // width

    public Position() {
        this(0, 0);
    }

    public Position(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void set(int y, int x) {
        setY(y);
        setX(x);
    }

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public void move(Direction d) {
        switch (d) {
            case UP -> {
                y += 1;
                System.out.println("Moving up.\n");
            }

            case DOWN -> {
                y -= 1;
                System.out.println("Moving down.\n");
            }

            case LEFT -> {
                x -= 1;
                System.out.println("Moving left.\n");
            }

            case RIGHT -> {
                x += 1;
                System.out.println("Moving right.\n");
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Position otherPosition)) {
            return false;
        }

        return ((y == otherPosition.y) && (x == otherPosition.x));
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }
}
