package eu.chrost;

import lombok.Getter;

@Getter
public class Point {
    private int x;
    private int y;

    public static Point point(int x, int y) {
        Point point = new Point();
        point.x = x;
        point.y = y;
        return point;
    }
}
