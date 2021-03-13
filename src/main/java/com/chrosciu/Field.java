package com.chrosciu;

import lombok.NonNull;
import lombok.Value;
import lombok.With;

@Value(staticConstructor = "of")
@With
public class Field {
    int x;
    int y;

    public Field shift(int shift, @NonNull Orientation orientation) {
        switch (orientation) {
            case VERTICAL:
                return this.withY(y + shift);
            case HORIZONTAL:
                return this.withX(x + shift);
            default:
                throw new IllegalArgumentException(String.format("Unknown orientation: %s", orientation));
        }
    }
}

