package eu.chrost;

class Field {
    private final int x;
    private final int y;

    public static Field of(int x, int y) {
        return new Field(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private Field(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
