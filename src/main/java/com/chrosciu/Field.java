package com.chrosciu;

public class Field {
    private final int x;
    private final int y;

    private Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Field of(int x, int y) {
        return new Field(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
