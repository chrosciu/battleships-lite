package eu.chrost;

import lombok.Getter;

public class ShipField {
    @Getter
    private final Field field;
    public boolean hit;

    public ShipField(Field field, boolean hit) {
        this.field = field;
        this.hit = hit;
    }
}
