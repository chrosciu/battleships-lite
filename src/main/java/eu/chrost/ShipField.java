package eu.chrost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ShipField {
    private final Field field;
    private boolean hit = false;

    public void markAsHit() {
        this.hit = true;
    }
}
