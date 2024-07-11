package eu.chrost;

public class Point {
    private int x;
    private int y;

    public static Point point(int x, int y) {
        Point point = new Point();
        point.x = x;
        point.y = y;
        return point;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
