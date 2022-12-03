package com.chrosciu;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Field {
    private final int x;
    private final int y;

    public static Field of(int x, int y) {
        return new Field(x, y);
    }
}
