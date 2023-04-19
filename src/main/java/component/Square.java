package component;

import util.GrassState;

import java.util.Random;

public class Square {

    private GrassState grassState;
    private int grassLength;

    public Square() {
        grassLength = new Random().nextInt(10 - 1 + 1) + 1;
        grassState = GrassState.UNCUT;
    }

    public int getGrassLength() {
        return grassLength;
    }

    public GrassState getGrassState() {
        return grassState;
    }

    public void mowGrass() {
        grassLength = 0;
        grassState = GrassState.CUT;
    }

    @Override
    public String toString() {
        return String.valueOf(grassLength);
    }
}
