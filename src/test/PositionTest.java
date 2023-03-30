package test;

import org.junit.jupiter.api.BeforeEach;
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
    void getXShouldEqual0() {
        assertEquals(0, p.getX());
    }

    @Test
    void getYShouldEqual0() {
        assertEquals(0, p.getY());
    }

    @Test
    void setX4ShouldEqualGetX4() {
        p.setX(4);
        assertEquals(4, p.getX());
    }

    @Test
    void setY3ShouldEqualGetX3() {
        p.setY(3);
        assertEquals(3, p.getY());
    }

    @Test
    void setIllegalMinX() {
        assertFalse(p.setX(-3));
    }

    @Test
    void setIllegalMaxX() {
        assertFalse(p.setX(5));
    }

    @Test
    void setIllegalMinY() {
        assertFalse(p.setY(-3));
    }

    @Test
    void setIllegalMaxY() {
        assertFalse(p.setY(6));
    }

    @Test
    void testToString() {
        assertEquals("(0,0)", p.toString());
    }

    @Test
    void legalMoveUp() {
        assertTrue(p.move(Direction.UP));
    }

    @Test
    void legalMoveDown() {
        p.setY(1);
        assertTrue(p.move(Direction.DOWN));
    }

    @Test
    void legalMoveLeft() {
        p.setX(1);
        assertTrue(p.move(Direction.LEFT));
    }

    @Test
    void legalMoveRight() {
        assertTrue(p.move(Direction.RIGHT));
    }

    @Test
    void illegalMoveUp() {
        p.setY(4);
        assertFalse(p.move(Direction.UP));
    }

    @Test
    void illegalMoveDown() {
        assertFalse(p.move(Direction.DOWN));
    }

    @Test
    void illegalMoveLeft() {
        assertFalse(p.move(Direction.LEFT));
    }

    @Test
    void illegalMoveRight() {
        p.setX(4);
        assertFalse(p.move(Direction.RIGHT));
    }
}
