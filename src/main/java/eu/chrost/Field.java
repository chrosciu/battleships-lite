package eu.chrost;

public record Field(int x, int y) {

    @Deprecated
    public static Field field(int x, int y) {
        return new Field(x, y);
    }

    public Field shiftInOrientation(int shift, Orientation orientation) {
        return switch (orientation) {
            case VERTICAL -> new Field(x, y + shift);
            case HORIZONTAL -> new Field(x + shift, y);
        };
    }
}
