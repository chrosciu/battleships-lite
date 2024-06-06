package eu.chrost;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Point {
    private final int x;
    private final int y;

    public static Point of(int x, int y) {
        return new Point(x, y);
    }
}
