package eu.chrost;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final List<ShipField> fields = new ArrayList<>();

    public void appendField(ShipField shipField) {
        fields.add(shipField);
    }

    public int length() {
        return fields.size();
    }

    public ShipField getField(int index) {
        return fields.get(index);
    }
}
