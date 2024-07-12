package eu.chrost;

public class ShipField {
    public Field field;
    public boolean hit;

    public static ShipField of(Field field, boolean hit) {
        ShipField shipField = new ShipField();
        shipField.field = field;
        shipField.hit = hit;
        return shipField;
    }
}
