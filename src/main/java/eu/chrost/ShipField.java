package eu.chrost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
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
