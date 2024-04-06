package eu.chrost;

class ShipField {
    private final Field field;
    private boolean hit = false;

    public static ShipField of(Field field) {
        return new ShipField(field);
    }

    public Field getField() {
        return field;
    }

    public boolean isHit() {
        return hit;
    }

    public void markAsHit() {
        hit = true;
    }

    private ShipField(Field field) {
        this.field = field;
    }
}
