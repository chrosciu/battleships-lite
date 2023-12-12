package eu.chrost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
class Ship {
    private final Field firstField;
    private final int length;
    private final Orientation orientation;

    public static Ship of(Field firstField, int length, Orientation orientation) {
        return new Ship(firstField, length, orientation);
    }
}
