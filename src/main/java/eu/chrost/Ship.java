package eu.chrost;

public record Ship(Field firstField, int length, Orientation orientation) {

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
