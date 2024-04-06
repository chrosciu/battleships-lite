package eu.chrost;

class ShipDefinition {
    private final Field firstField;
    private final int length;
    private final Orientation orientation;

    public static ShipDefinition of(Field firstField, int length, Orientation orientation) {
        return new ShipDefinition(firstField, length, orientation);
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

    private ShipDefinition(Field firstField, int length, Orientation orientation) {
        this.firstField = firstField;
        this.length = length;
        this.orientation = orientation;
    }

}
