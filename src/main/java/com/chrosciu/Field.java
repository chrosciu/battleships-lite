package com.chrosciu;

import lombok.Getter;

@Getter
public class Field {
    private int x;
    private int y;

    public static Field of(int x, int y) {
        Field field = new Field();
        field.x = x;
        field.y = y;
        return field;
    }
}
