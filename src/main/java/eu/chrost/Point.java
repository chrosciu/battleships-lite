package eu.chrost;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Point {
    private final int x;
    private final int y;

    @Deprecated
    public static Point point(int x, int y) {
        return new Point(x, y);
    }
}
