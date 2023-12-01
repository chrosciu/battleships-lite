package com.chrosciu;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
@Getter
public class Ship {
    @NonNull
    private final Field firstField;
    private final int length;
    @NonNull
    private final Direction direction;

    public static Ship of(Field firstField, int length, Direction direction) {
        return new Ship(firstField, length, direction);
    }
}
