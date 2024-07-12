package eu.chrost;

import java.util.ArrayList;
import java.util.List;

import static eu.chrost.Result.HIT;
import static eu.chrost.Result.MISSED;
import static eu.chrost.Result.SUNK;

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

    public boolean isSunk() {
        boolean isSunk = true;
        for (int j = 0; j < size() && isSunk; ++j) {
            isSunk &= get(j).isHit();
        }
        return isSunk;
    }

    Result takeShot(Field field) {
        var result = MISSED;
        for (int j = 0; j < size(); ++j) {
            if (get(j).getField().equals(field)) {
                get(j).markAsHit();
                result = HIT;
                break;
            }
        }
        if (HIT == result) {
            if (isSunk()) {
                result = SUNK;
            }
        }
        return result;
    }
}
