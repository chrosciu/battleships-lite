package com.chrosciu;

import lombok.Value;

@Value(staticConstructor = "of")
public class Ship {
    Shooter.Point firstField;
    int length;
    Direction direction;
}

