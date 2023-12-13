package eu.chrost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@Getter
class ShipDefinition {
    private final Field firstField;
    private final int length;
    private final Orientation orientation;
}
