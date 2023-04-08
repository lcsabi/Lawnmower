package component;

import util.BladeState;
import util.Direction;
import util.Position;
import util.PowerState;

public class Lawnmower { // TODO: test new methods

    private final Position pos;
    private double batteryCharge = 100.0;
    private final double mowConsumption;
    private final double moveConsumption;
    private PowerState powerState = PowerState.OFF;
    private BladeState bladeState = BladeState.OFF;

    public Lawnmower(int X_MAX, int Y_MAX) {
        pos = new Position(X_MAX, Y_MAX);
        mowConsumption = 4.0;
        moveConsumption = 2.0;
    }

    public Lawnmower(int X_MAX, int Y_MAX, double mowConsumption, double moveConsumption) {
        pos = new Position(X_MAX, Y_MAX);
        this.mowConsumption = mowConsumption;
        this.moveConsumption = moveConsumption;
    }
    public Position getPos() {
        return pos;
    }

    public double getBatteryCharge() {
        return batteryCharge;
    }

    public PowerState getPowerState() {
        return powerState;
    }

    public BladeState getBladeState() {
        return bladeState;
    }

    private void printStatus() {
        System.out.println(
                "Position: " + getPos()
                + "\nBattery: " + batteryCharge + "%"
                + "\nPower: " + (powerState == PowerState.ON ? "ON" : "OFF")
                + "\nBlades: " + (bladeState == BladeState.ON ? "ON" : "OFF")
        );
    }

    public void mow(Square square) {
        if (powerState == PowerState.OFF) {
            switchPower();
        }
        if (bladeState == BladeState.OFF) {
            switchBlades();
        }

        batteryCharge -= square.getGrassLength() * mowConsumption;

        square.mowGrass();
        System.out.println("Mowing grass.");
        printStatus();
    }

    public boolean move(Direction d) {
        boolean result = pos.move(d);
        if (result) {
            if (powerState == PowerState.OFF) {
                switchPower();
            }
            if (bladeState == BladeState.ON) {
                switchBlades();
            }

            batteryCharge -= moveConsumption;
        }
        printStatus();
        return result;
    }

    public void switchPower() {
        if (powerState == PowerState.OFF) {
            powerState = PowerState.ON;
            System.out.println("Lawnmower turning on.");
        } else {
            powerState = PowerState.OFF;
            System.out.println("Lawnmower turning off.");
        }
    }

    public void switchBlades() {
        if (bladeState == BladeState.OFF) {
            bladeState = BladeState.ON;
            System.out.println("Blades starting.");
        } else {
            bladeState = BladeState.OFF;
            System.out.println("Blades stopping.");
        }
    }

    @Override
    public String toString() {
        return "X";
    }
}
