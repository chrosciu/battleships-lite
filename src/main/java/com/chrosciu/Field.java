package com.chrosciu;

import lombok.NonNull;
import lombok.Value;
import lombok.With;

@Value(staticConstructor = "of")
@With
public class Field {
    int x;
    int y;

    public Field shiftInDirection(@NonNull Direction direction, int shift) {
        switch (direction) {
            case HORIZONTAL:
                return this.withX(x + shift);
            case VERTICAL:
                return this.withY(y + shift);
            default:
                throw new IllegalArgumentException(String.format("Unexpected direction: %s", direction));
        }
    }
}
