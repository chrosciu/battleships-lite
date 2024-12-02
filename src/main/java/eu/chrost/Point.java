package eu.chrost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
class Point {
    private final int x;
    private final int y;

    public static Point point(int x, int y) {
        Point point = new Point(x, y);
        return point;
    }
}
