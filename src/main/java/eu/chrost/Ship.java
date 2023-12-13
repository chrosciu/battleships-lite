package eu.chrost;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final List<ShipField> fields = new ArrayList<>();

    public void add(ShipField shipField) {
        fields.add(shipField);
    }

    public int size() {
        return fields.size();
    }

    public ShipField get(int index) {
        return fields.get(index);
    }
}
