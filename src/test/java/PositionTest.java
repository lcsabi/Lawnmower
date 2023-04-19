import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import util.Direction;
import util.Position;

class PositionTest {

    Position p;

    @BeforeEach
    void setUp() {
        p = new Position();
    }

    @Test
    @DisplayName("Moving right from (0,0) -> (0,1).")
    void moveRight() {
        System.out.println("Coordinates before moving: " + p);
        p.move(Direction.RIGHT);
        assertEquals(new Position(0,1), p);
        System.out.println("Coordinates after moving: " + p);
    }

    @Test
    @DisplayName("Moving up from (0,0) -> (1,0).")
    void moveUp() {
        System.out.println("Coordinates before moving: " + p);
        p.move(Direction.UP);
        assertEquals(new Position(1,0), p);
    }

    @Test
    @DisplayName("Moving left from (0,1) -> (0,0).")
    void moveLeft() {
        p.set(0,1);
        System.out.println("Coordinates before moving: " + p);
        p.move(Direction.LEFT);
        assertEquals(new Position(0,0), p);
    }

    @Test
    @DisplayName("Moving down from (1,0) -> (0,0).")
    void moveDown() {
        p.set(1,0);
        System.out.println("Coordinates before moving: " + p);
        p.move(Direction.DOWN);
        assertEquals(new Position(0,0), p);
    }

    @Test
    @DisplayName("Testing parameterized ctor.")
    void otherCtor() {
        p = new Position(1,1);
        assertEquals(new Position(1,1), p);
    }

    @Test
    @DisplayName("Testing equals() when equal.")
    void testEqualsTrue() {
        Position a = new Position(10, 5);
        Position b = new Position(10, 5);
        assertEquals(a, b);
    }

    @Test
    @DisplayName("Testing equals() when not equal.")
    void testEqualsFalse() {
        Position a = new Position(0, 0);
        Position b = new Position(1, 1);
        assertNotEquals(a, b);
    }
}
