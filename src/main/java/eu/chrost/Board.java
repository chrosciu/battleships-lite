package eu.chrost;

import java.util.List;
import java.util.stream.Collectors;

import static eu.chrost.Result.*;

public class Board {

    private final List<Ship> ships;

    public Board(List<ShipDefinition> shipDefinitions) {
        ships = shipDefinitions.stream()
                .map(shipDefinition -> buildShipFromDefinition(shipDefinition))
                .collect(Collectors.toList());
    }

    private Ship buildShipFromDefinition(ShipDefinition shipDefinition) {
        Ship ship = new Ship();
        for (int j = 0; j < shipDefinition.getLength(); ++j) {
            ship.appendField(new ShipField(shipDefinition.getFirstField().shiftBy(j, shipDefinition.getOrientation())));
        }
        return ship;
    }

    public Result applyShot(Field field) {
        Result result = MISSED;
        for (int i = 0; i < ships.size() && MISSED == result; ++i) {
            Ship ship = ships.get(i);
            result = applyShotForShip(field, ship);
        }
        if (areAllShipsSunk()) {
            result = FINISHED;
        }
        return result;
    }

    private Result applyShotForShip(Field field, Ship ship) {
        Result result = MISSED;
        for (int j = 0; j < ship.length() && MISSED == result; ++j) {
            ShipField shipField = ship.getField(j);
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

    private boolean isShipSunk(Ship ship) {
        boolean isShipSunk = true;
        for (int j = 0; j < ship.length() && isShipSunk; ++j) {
            ShipField shipField = ship.getField(j);
            isShipSunk = shipField.isHit();
        }
        return isShipSunk;
    }

    private boolean areAllShipsSunk() {
        boolean allShipsSunk = true;
        for (int i = 0; i < ships.size() && allShipsSunk; ++i) {
            for (int j = 0; j < ships.get(i).length() && allShipsSunk; ++j) {
                allShipsSunk = ships.get(i).getField(j).isHit();
            }
        }
        return allShipsSunk;
    }
}
