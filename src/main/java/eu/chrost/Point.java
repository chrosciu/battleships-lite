package eu.chrost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Point {
    private final int x;
    private final int y;

    public static Point of(int x, int y) {
        return new Point(x, y);
    }
}
