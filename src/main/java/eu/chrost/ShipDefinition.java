package eu.chrost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ShipDefinition {
    private final Field firstField;
    private final int length;
    private final Orientation orientation;
}
