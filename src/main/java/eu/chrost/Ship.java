package eu.chrost;

public class Ship {
    private Field p;
    private int l;
    private Orientation orientation;

    public static Ship of(Field p, int l, Orientation orientation) {
        Ship ship = new Ship();
        ship.p = p;
        ship.l = l;
        ship.orientation = orientation;
        return ship;
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
