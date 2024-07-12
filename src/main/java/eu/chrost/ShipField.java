package eu.chrost;

public class ShipField {
    public Field p;
    public boolean h;

    public static ShipField of(Field p, boolean h) {
        ShipField shipField = new ShipField();
        shipField.p = p;
        shipField.h = h;
        return shipField;
    }
}
