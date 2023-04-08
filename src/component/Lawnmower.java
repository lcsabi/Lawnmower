package component;

import util.Direction;
import util.Position;

public class Lawnmower {

    private final Position pos;
    private double batteryCharge = 100.0;
    private final double mowConsumption;
    private final double moveConsumption;

    public Lawnmower(int X_MAX, int Y_MAX) {
        pos = new Position(X_MAX, Y_MAX);
        mowConsumption = 3.0;
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

    public void mow(Square square) {
        batteryCharge -= square.getGrassLength() * mowConsumption;
        square.mowGrass();
    }

    public boolean move(Direction d) {
        boolean result = pos.move(d);
        if (result) {
            batteryCharge -= moveConsumption;
        }
        return result;
    }

    @Override
    public String toString() {
        return "X";
    }
}
