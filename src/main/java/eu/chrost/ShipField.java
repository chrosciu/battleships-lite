package eu.chrost;

import lombok.Getter;

@Getter
public class ShipField {
    private final Field field;
    private boolean hit;

    public ShipField(Field field) {
        this.field = field;
        this.hit = false;
    }

    public void markAsHit() {
        this.hit = true;
    }
}
