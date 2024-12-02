package eu.chrost;

import lombok.Value;

@Value
class Field {
    int x;
    int y;

    public Field shift(int shiftLength, Orientation orientation) {
        switch (orientation) {
            case VERTICAL:
                return new Field(x, y + shiftLength);
            case HORIZONTAL:
                return new Field(x + shiftLength, y);
            default:
                throw new IllegalArgumentException("Invalid orientation: " + orientation);

        }
    }
}
