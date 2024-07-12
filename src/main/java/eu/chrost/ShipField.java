package eu.chrost;

import lombok.Getter;
import lombok.Setter;

public class ShipField {
    @Getter
    private final Field field;
    @Getter
    @Setter
    private boolean hit;

    public ShipField(Field field, boolean hit) {
        this.field = field;
        this.hit = hit;
    }
}
