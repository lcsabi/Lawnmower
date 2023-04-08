package test;

import component.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import component.Lawnmower;
import util.Direction;

import static org.junit.jupiter.api.Assertions.*;

class LawnmowerTest {

    Lawnmower lawnmower;

    @BeforeEach
    void setUp() {
        lawnmower = new Lawnmower(5,5);
    }

    @Test
    @DisplayName("Testing initial position. Should return (0,0).")
    void getPosInitially() {
        int x = lawnmower.getPos().getX();
        int y = lawnmower.getPos().getY();
        assertAll(
                () -> assertEquals(0, x),
                () -> assertEquals(0, y)
        );
    }

    @Test
    @DisplayName("Testing getBatteryCharge initially. Should return 100.0.")
    void getBatteryChargeInitially() {
        assertEquals(100, (int)lawnmower.getBatteryCharge());
    }

    @Test
    @DisplayName("Testing mow(). Battery -= square.getGrassLength() * mowConsumption.")
    void testMow() {
        Square square = new Square();
        int grassLength = square.getGrassLength();
        lawnmower.mow(square);
        assertEquals(100, lawnmower.getBatteryCharge() + grassLength * 4); // mowConsumption is assumed = 4 by default
    }

    @Test
    @DisplayName("Testing legally moving up. Should return true.")
    void testLegalMoveUp() {
        assertTrue(lawnmower.move(Direction.UP));
    }

    @Test
    @DisplayName("Testing illegally moving up. Should return false.")
    void testIllegalMoveUp() {
        lawnmower.getPos().setY(4);
        assertFalse(lawnmower.move(Direction.UP));
    }

    @Test
    @DisplayName("Testing legally moving right. Should return true.")
    void testLegalMoveRight() {
        assertTrue(lawnmower.move(Direction.RIGHT));
    }

    @Test
    @DisplayName("Testing illegally moving right. Should return false.")
    void testIllegalMoveRight() {
        lawnmower.getPos().setX(4);
        assertFalse(lawnmower.move(Direction.RIGHT));
    }

    @Test
    @DisplayName("Testing legally moving down. Should return true.")
    void testLegalMoveDown() {
        lawnmower.getPos().setY(1);
        assertTrue(lawnmower.move(Direction.DOWN));
    }

    @Test
    @DisplayName("Testing illegally moving right. Should return false.")
    void testIllegalMoveDown() {
        assertFalse(lawnmower.move(Direction.DOWN));
    }

    @Test
    @DisplayName("Testing legally moving left. Should return true.")
    void testLegalMoveLeft() {
        lawnmower.getPos().setX(1);
        assertTrue(lawnmower.move(Direction.LEFT));
    }

    @Test
    @DisplayName("Testing illegally moving left. Should return false.")
    void testIllegalMoveLeft() {
        assertFalse(lawnmower.move(Direction.LEFT));
    }

    @Test
    @DisplayName("Testing getPos() after moving to the right then moving up. Should return (1,1).")
    void testGetPosAfterMoving() {
        lawnmower.move(Direction.RIGHT);
        lawnmower.move(Direction.UP);
        assertEquals("(1,1)", lawnmower.getPos().toString());
    }

    @Test
    @DisplayName("Testing toString(). Should return the letter X.")
    void testToString() {
        assertEquals("X", lawnmower.toString());
    }


}
