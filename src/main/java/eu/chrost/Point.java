package eu.chrost;

class Point {
    private final int x;
    private final int y;

    public static Point of(int x, int y) {
        return new Point(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
