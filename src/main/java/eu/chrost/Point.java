package eu.chrost;

class Point {
    public int x;
    public int y;

    public static Point point(int x, int y) {
        Point point = new Point();
        point.x = x;
        point.y = y;
        return point;
    }
}
