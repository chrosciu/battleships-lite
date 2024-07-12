package eu.chrost;

import java.util.ArrayList;
import java.util.List;

import static eu.chrost.Result.FINISHED;
import static eu.chrost.Result.HIT;
import static eu.chrost.Result.MISSED;
import static eu.chrost.Result.SUNK;

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
        //iterate through all ships
        for (int i = 0; i < ships.size() && MISSED == result; ++i) {
            //iterate through all ship fields
            for (int j = 0; j < ships.get(i).size() && MISSED == result; ++j) {
                //if any of ship fields is equal to passed field - mark as hit
                if (ships.get(i).get(j).getField().equals(field)) {
                    ships.get(i).get(j).markAsHit();
                    result = HIT;
                }
            }
            //if ship is hit - check if it is sunk
            if (HIT == result) {
                //iterate through all fields and check if they are all hit
                boolean isSunk = true;
                for (int j = 0; j < ships.get(i).size() && isSunk; ++j) {
                    isSunk &= ships.get(i).get(j).isHit();
                }
                if (isSunk) {
                    result = SUNK;
                }
            }
        }
        //check if all ships are sunk
        boolean isFinished = true;
        for (int i = 0; i < ships.size() && isFinished; ++i) {
            for (int j = 0; j < ships.get(i).size() && isFinished; ++j) {
                isFinished &= ships.get(i).get(j).isHit();
            }
        }
        if (isFinished) {
            result = FINISHED;
        }
        return result;
    }
}
