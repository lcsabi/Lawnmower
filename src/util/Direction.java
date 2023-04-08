package util;

public enum Direction {

    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static Direction getOppositeDirection(Direction d) { // TODO: test
        return switch(d) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
