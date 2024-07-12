package eu.chrost;

public class Ship {
    private Field p;
    private int l;
    private Orientation orientation;

    public Ship(Field p, int l, Orientation orientation) {
        this.p = p;
        this.l = l;
        this.orientation = orientation;
    }

    public Field getP() {
        return p;
    }

    public int getL() {
        return l;
    }

    public Orientation getOrientation() {
        return orientation;
    }

}
