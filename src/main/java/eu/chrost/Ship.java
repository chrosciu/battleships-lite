package eu.chrost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
class Ship {
    private final Field firstField;
    private final int length;
    private final Orientation orientation;

    // static constructor
}
