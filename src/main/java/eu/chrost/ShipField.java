package eu.chrost;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ShipField {
    private final Field field;

    private boolean hit = false;

    public static ShipField of(Field field) {
        return new ShipField(field);
    }

    public void markAsHit() {
        this.hit = true;
    }
}
