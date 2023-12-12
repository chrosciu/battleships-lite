package eu.chrost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
class ShipField {
    private final Field field;
    private boolean hit = false;

    public static ShipField of(Field field) {
        return new ShipField(field);
    }

    public void markAsHit() {
        hit = true;
    }
}
