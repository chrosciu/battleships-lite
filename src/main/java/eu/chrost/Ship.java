package eu.chrost;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final List<ShipField> shipFields = new ArrayList<>();

    public void add(ShipField shipField) {
        shipFields.add(shipField);
    }

    public int size() {
        return shipFields.size();
    }

    public ShipField get(int index) {
        return shipFields.get(index);
    }
}
