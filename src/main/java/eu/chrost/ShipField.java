package eu.chrost;

public class ShipField {
    public Field field;
    public boolean hit;

    public static ShipField of(Field p, boolean h) {
        ShipField pointH = new ShipField();
        pointH.field = p;
        pointH.hit = h;
        return pointH;
    }
}
