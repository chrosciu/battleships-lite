package com.chrosciu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class Field {
    private final int x;
    private final int y;

    public static Field of(int x, int y) {
        return new Field(x, y);
    }
}
