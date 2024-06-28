package eu.chrost;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class Field {
    private final int x;
    private final int y;

    @Deprecated
    public static Field field(int x, int y) {
        return new Field(x, y);
    }

    public Field shiftInOrientation(int shift, Orientation orientation) {
        return switch (orientation) {
            case VERTICAL -> new Field(x, y + shift);
            case HORIZONTAL -> new Field(x + shift, y);
        };
    }
}
