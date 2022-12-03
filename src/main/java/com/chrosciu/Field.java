package com.chrosciu;

public class Field {
    public int x;
    public int y;

    public static Field of(int x, int y) {
        Field field = new Field();
        field.x = x;
        field.y = y;
        return field;
    }
}
