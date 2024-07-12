package eu.chrost;

public class Ship {
    private Field firstField;
    private int length;
    private Orientation orientation;

    public Ship(Field firstField, int length, Orientation orientation) {
        this.firstField = firstField;
        this.length = length;
        this.orientation = orientation;
    }

    public Field getFirstField() {
        return firstField;
    }

    public int getLength() {
        return length;
    }

    public Orientation getOrientation() {
        return orientation;
    }

}
