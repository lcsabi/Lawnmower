package component;

import util.GrassState;

import java.util.Random;

public class Square {

    private GrassState grassState;
    private int grassLength;

    public Square() {
        grassState = GrassState.UNCUT;
        grassLength = new Random().nextInt(11);
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
