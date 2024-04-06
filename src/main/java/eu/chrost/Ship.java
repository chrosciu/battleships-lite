package eu.chrost;

class Ship {
    private final Field firstField;
    private final int length;
    private final Orientation orientation;

    public static Ship of(Field firstField, int length, Orientation orientation) {
        return new Ship(firstField, length, orientation);
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

    private Ship(Field firstField, int length, Orientation orientation) {
        this.firstField = firstField;
        this.length = length;
        this.orientation = orientation;
    }

}
