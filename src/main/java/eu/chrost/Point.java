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
        switch (orientation) {
            case VERTICAL:
                return new Point(x, y + shift);
            case HORIZONTAL:
                return new Point(x + shift, y);
            default:
                throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }
    }
}
