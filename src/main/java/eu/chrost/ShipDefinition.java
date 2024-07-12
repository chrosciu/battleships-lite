package eu.chrost;

public record ShipDefinition(Field firstField, int length, Orientation orientation) {
    public Ship toShip() {
        Ship ship = new Ship();
        for (int j = 0; j < length(); ++j) {
            var firstField = firstField();
            var orientation = orientation();
            var shiftedField = firstField.shift(j, orientation);
            ship.add(new ShipField(shiftedField));
        }
        return ship;
    }
}
