package eu.chrost;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
@EqualsAndHashCode
public class Field {
    private final int x;
    private final int y;

    public static Field of(int x, int y) {
        return new Field(x, y);
    }

    public Field shift(int shift, Orientation orientation) {
        switch (orientation) {
            case VERTICAL:
                return of(x, y + shift);
            case HORIZONTAL:
                return of(x + shift, y);
            default:
                throw new IllegalArgumentException("Unknown orientation type: " + orientation);
        }
    }
}
