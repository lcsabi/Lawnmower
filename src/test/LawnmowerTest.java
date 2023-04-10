package test;

import component.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import component.Lawnmower;
import util.BladeState;
import util.Direction;
import util.Position;
import util.PowerState;

import static org.junit.jupiter.api.Assertions.*;

class LawnmowerTest {

    Lawnmower lawnmower;

    @BeforeEach
    void setUp() {
        lawnmower = new Lawnmower();
    }

    @Test
    @DisplayName("Testing initial position. Should return (0,0).")
    void getInitialPos() {
        assertEquals(new Position(0, 0),lawnmower.getCurrentPos());
    }

    @Test
    @DisplayName("Testing initial batteryCharge. Should return 100.")
    void getBatteryChargeInitially() {
        assertEquals(100, (int)lawnmower.getBatteryCharge());
    }

    @Test
    @DisplayName("Testing depletion of battery by 25%. Should return 75.")
    void testDepletion() {
        lawnmower.depleteBattery(25.0);
        assertEquals(75, (int)lawnmower.getBatteryCharge());
    }

    @Test
    @DisplayName("Testing mow(). Battery -= square.getGrassLength() * mowConsumption.")
    void testMow() {
        Square square = new Square();
        int grassLength = square.getGrassLength();
        lawnmower.mow(square);
        assertEquals(100,
                lawnmower.getBatteryCharge()
                + grassLength
                * lawnmower.getMowConsumption()
        );
    }

    @Test
    @DisplayName("Testing toString(). Should return the letter X.")
    void testToString() {
        assertEquals("X", lawnmower.toString());
    }

    @Test
    @DisplayName("Testing switchPower() when power is OFF.")
    void switchPowerToOn() {
        lawnmower.switchPower();
        assertEquals(PowerState.ON, lawnmower.getPowerState());
    }

    @Test
    @DisplayName("Testing switchPower() when power is ON.")
    void switchPowerToOff() {
        lawnmower.switchPower();
        lawnmower.switchPower();
        assertEquals(PowerState.OFF, lawnmower.getPowerState());
    }

    @Test
    @DisplayName("Testing getter methods for powerState and bladeState when they are both OFF.")
    void testGetStatesWhenOFF() {
        assertAll(
                () -> assertEquals(PowerState.OFF, lawnmower.getPowerState()),
                () -> assertEquals(BladeState.OFF, lawnmower.getBladeState())
        );
    }

    @Test
    @DisplayName("Testing getter methods for powerState and bladeState when they are both ON.")
    void testGetStatesWhenON() {
        lawnmower.switchPower();
        lawnmower.switchBlades();
        assertAll(
                () -> assertEquals(PowerState.ON, lawnmower.getPowerState()),
                () -> assertEquals(BladeState.ON, lawnmower.getBladeState())
        );
    }

    @Test
    @DisplayName("Testing switchBlades() when blades are OFF.")
    void switchBladesToOn() {
        lawnmower.switchBlades();
        assertEquals(BladeState.ON, lawnmower.getBladeState());
    }

    @Test
    @DisplayName("Testing switchPower() when power is ON.")
    void switchBladesToOff() {
        lawnmower.switchBlades();
        lawnmower.switchBlades();
        assertEquals(BladeState.OFF, lawnmower.getBladeState());
    }

    @Test
    @DisplayName("Testing moving to the right.")
    void moveRight() {
        lawnmower.move(Direction.RIGHT);
        assertEquals(new Position(0,1), lawnmower.getCurrentPos());
    }
}
