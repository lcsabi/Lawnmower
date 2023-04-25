package component;

import util.BladeState;
import util.Direction;
import util.Position;
import util.PowerState;

public class Lawnmower {

    private final Position currentPos;
    private double batteryCharge = 100.0;
    private final double mowConsumption;
    private final double moveConsumption;
    private PowerState powerState = PowerState.OFF;
    private BladeState bladeState = BladeState.OFF;

    public Lawnmower() {
        this(2.0, 1.0);
    }

    public Lawnmower(double mowConsumption, double moveConsumption) {
        currentPos = new Position();
        this.mowConsumption = mowConsumption;
        this.moveConsumption = moveConsumption;
    }

    public Position getCurrentPos() {
        return currentPos;
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

    public double getMowConsumption() {
        return mowConsumption;
    }

    public double getMoveConsumption() {
        return moveConsumption;
    }

    public void printStatus() {
        System.out.println(
                "Position: " + getCurrentPos()
                + "\nBattery: " + batteryCharge + "%"
                + "\nPower: " + (powerState == PowerState.ON ? "ON" : "OFF")
                + "\nBlades: " + (bladeState == BladeState.ON ? "ON" : "OFF\n")
        );
    }

    public void mow(Square square) {
        if (powerState == PowerState.OFF) {
            switchPower();
        }
        if (bladeState == BladeState.OFF) {
            switchBlades();
        }

        depleteBattery(square.getGrassLength() * mowConsumption);

        square.mowGrass();
        System.out.println("Cutting grass at " + getCurrentPos());
    }

    public void move(Direction d) {
        depleteBattery(moveConsumption);
        currentPos.move(d);
    }

    public void depleteBattery(double amount) {
        batteryCharge -= amount;
    }

    public void rechargeBattery() {
        System.out.println("Recharging battery.");
        powerState = PowerState.RECHARGING;
        while (batteryCharge < 100) {
            batteryCharge += 20;
            if (batteryCharge > 100) {
                batteryCharge = 100;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }
            System.out.println("Battery: " + batteryCharge + "%");
        }
        System.out.println("Battery recharged.");
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
