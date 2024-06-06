package eu.chrost;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static eu.chrost.Orientation.VERTICAL;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Field {
    private final int x;
    private final int y;

    public static Field of(int x, int y) {
        return new Field(x, y);
    }

    public Field shift(int shift, Orientation orientation) {
        if (orientation == VERTICAL) {
            return of(x, y + shift);
        } else {
            return of(x + shift, y);
        }
    }
}
