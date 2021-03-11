package com.chrosciu;

import lombok.Value;

@Value(staticConstructor = "of")
public class Ship {
    Field firstField;
    int length;
    Direction direction;
}
