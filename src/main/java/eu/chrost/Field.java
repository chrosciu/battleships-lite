package eu.chrost;

public record Field(int x, int y) {
    public Field shift(int shift, Orientation orientation) {
        return switch (orientation) {
            case VERTICAL -> new Field(x, y + shift);
            case HORIZONTAL -> new Field(x + shift, y);
        };
    }
}
