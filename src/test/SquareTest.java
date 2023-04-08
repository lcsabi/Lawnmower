package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import component.Square;
import util.GrassState;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    Square square;
    @BeforeEach
    void setUp() {
        square = new Square();
    }

    @Test
    @DisplayName("Testing getGrassLength(). Returned value should be between 1 and 10")
    void testGetGrassLength() {
        int length = square.getGrassLength();
        assertTrue(length > 0 && length < 11);
    }

    @Test
    @DisplayName("Testing mow() on a Square. grassState should be CUT after.")
    void testMowGrass() {
        square.mowGrass();
        assertEquals(GrassState.CUT, square.getGrassState());
    }

    @Test
    @DisplayName("Testing getGrassState() on UNCUT square. Should return UNCUT.")
    void testGetUncutGrassState() {
        assertEquals(GrassState.UNCUT, square.getGrassState());
    }

    @Test
    @DisplayName("Testing getGrassState() on CUT square. Should return CUT.")
    void testGetCutGrassState() {
        square.mowGrass();
        assertEquals(GrassState.CUT, square.getGrassState());
    }

    @Test
    @DisplayName("Testing toString() on a square which should return the length of the square.")
    void testToString() {
        int length = square.getGrassLength();
        assertEquals(String.valueOf(length), square.toString());
    }
}
