package eu.chrost;

import java.util.ArrayList;
import java.util.List;

import static eu.chrost.Result.FINISHED;
import static eu.chrost.Result.MISSED;

public class Shooter {

    private List<Ship> ships = new ArrayList<>();

    /**
     * Initialize shooter with given list of ship definitions
     */
    public Shooter(List<ShipDefinition> shipDefinitions) {
        for (var shipDefinition : shipDefinitions) {
            Ship ship = shipDefinition.toShip();
            ships.add(ship);
        }
    }

    /**
     * Take shot for given field and return shot result
     *
     * @param field - field coordinates
     * @return - shot result
     */
    public Result shoot(Field field) {
        var result = MISSED;
        for (var ship : ships) {
            result = ship.takeShot(field);
            if (result != MISSED) {
                break;
            }
        }
        if (ShipUtil.areGivenShipsSunk(ships)) {
            result = FINISHED;
        }
        return result;
    }
}
