package eu.chrost;

import lombok.Getter;

@Getter
public class ShipField {
    private final Field field;
    private boolean hit = false;

    public ShipField(Field field) {
        this.field = field;
    }

    public void markAsHit() {
        this.hit = true;
    }
}
