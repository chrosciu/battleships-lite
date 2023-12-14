package eu.chrost;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static eu.chrost.Result.*;

public class Board {

    private final List<List<ShipField>> ships;

    public Board(List<ShipDefinition> shipDefinitions) {
        ships = shipDefinitions.stream()
                .map(shipDefinition -> buildShipFromDefinition(shipDefinition))
                .collect(Collectors.toList());
    }

    private List<ShipField> buildShipFromDefinition(ShipDefinition shipDefinition) {
        List<ShipField> ship = new ArrayList<>();
        for (int j = 0; j < shipDefinition.getLength(); ++j) {
            ship.add(new ShipField(shipDefinition.getFirstField().shiftBy(j, shipDefinition.getOrientation())));
        }
        return ship;
    }

    public Result applyShot(Field field) {
        Result result = MISSED;
        for (int i = 0; i < ships.size() && MISSED == result; ++i) {
            List<ShipField> ship = ships.get(i);
            result = applyShotForShip(field, ship);
        }
        if (areAllShipsSunk()) {
            result = FINISHED;
        }
        return result;
    }

    private Result applyShotForShip(Field field, List<ShipField> ship) {
        Result result = MISSED;
        for (int j = 0; j < ship.size() && MISSED == result; ++j) {
            ShipField shipField = ship.get(j);
            if (shipField.getField().equals(field)) {
                shipField.markAsHit();
                result = HIT;
            }
        }
        if (HIT == result) {
            if (isShipSunk(ship)) {
                result = SUNK;
            }
        }
        return result;
    }

    private boolean isShipSunk(List<ShipField> ship) {
        boolean isShipSunk = true;
        for (int j = 0; j < ship.size() && isShipSunk; ++j) {
            ShipField shipField = ship.get(j);
            isShipSunk = shipField.isHit();
        }
        return isShipSunk;
    }

    private boolean areAllShipsSunk() {
        boolean allShipsSunk = true;
        for (int i = 0; i < ships.size() && allShipsSunk; ++i) {
            for (int j = 0; j < ships.get(i).size() && allShipsSunk; ++j) {
                allShipsSunk = ships.get(i).get(j).isHit();
            }
        }
        return allShipsSunk;
    }
}
