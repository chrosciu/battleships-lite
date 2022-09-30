package com.chrosciu;

import lombok.Getter;

@Getter
public class FieldWithHitMark {
    private final Shooter.Point field;
    private boolean hit;

    public FieldWithHitMark(Shooter.Point field) {
        this.field = field;
    }

    public void markAsHit() {
        hit = true;
    }
}
