package eu.chrost;

public class Point {
    private final int x;
    private final int y;

    public static Point point(int x, int y) {
        Point point = new Point(x, y);
        return point;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
