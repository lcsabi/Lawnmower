package util;

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
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Position otherPosition = (Position) other;

        if (y != otherPosition.y) return false;
        return x == otherPosition.x;
    }

    @Override
    public int hashCode() {
        int result = y;
        result = 31 * result + x;
        return result;
    }
}
