package eu.chrost;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ShipField {
    private Field field;
    @Setter
    private boolean hit;

    public static ShipField of(Field p, boolean h) {
        ShipField pointH = new ShipField();
        pointH.field = p;
        pointH.hit = h;
        return pointH;
    }
}
