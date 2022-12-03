package com.chrosciu;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Ship {
    private final Field firstField;
    private final int length;
    private final Direction direction;

    public static Ship of(Field firstField, int length, Direction direction) {
        return new Ship(firstField, length, direction);
    }
}
