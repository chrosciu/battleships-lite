package eu.chrost;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class Point {
    private final int x;
    private final int y;

    @Deprecated
    public static Point point(int x, int y) {
        return new Point(x, y);
    }

    public Point shiftInOrientation(int shift, Orientation orientation) {
        return switch (orientation) {
            case VERTICAL -> new Point(x, y + shift);
            case HORIZONTAL -> new Point(x + shift, y);
        };
    }
}
