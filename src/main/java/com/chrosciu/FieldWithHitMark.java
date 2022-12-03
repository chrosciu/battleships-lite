package com.chrosciu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FieldWithHitMark {
    private final Field field;
    private boolean hit = false;

    public void markAsHit() {
        hit = true;
    }
}
