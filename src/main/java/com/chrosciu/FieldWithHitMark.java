package com.chrosciu;

import lombok.Getter;

@Getter
public class FieldWithHitMark {
    private final Field field;
    private boolean hit = false;

    private FieldWithHitMark(Field field) {
        this.field = field;
    }

    public static FieldWithHitMark of(Field field) {
        return new FieldWithHitMark(field);
    }

    public void markAsHit() {
        this.hit = true;
    }
}
