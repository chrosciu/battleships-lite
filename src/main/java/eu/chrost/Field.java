package eu.chrost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Field {
    private final int x;
    private final int y;

    public Field shiftBy(int distance, Orientation orientation) {
        switch (orientation) {
            case VERTICAL:
                return new Field(x, y + distance);
            case HORIZONTAL:
                return new Field(x + distance, y);
            default:
                throw new IllegalArgumentException("Unknown orientation");
        }
    }
}
