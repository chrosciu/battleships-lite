package com.chrosciu;

import lombok.Value;

@Value(staticConstructor = "of")
public class Field {
    int x;
    int y;
}


