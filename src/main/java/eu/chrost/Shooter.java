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
            Ship ship = new Ship();
            for (int j = 0; j < shipDefinition.length(); ++j) {
                var firstField = shipDefinition.firstField();
                var orientation = shipDefinition.orientation();
                var shiftedField = firstField.shift(j, orientation);
                ship.add(new ShipField(shiftedField));
            }
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
                boolean a = true;
                for (int j = 0; j < ships.get(i).size() && a; ++j) {
                    a &= ships.get(i).get(j).isHit();
                }
                if (a) {
                    result = SUNK;
                }
            }
        }
        //check if all ships are sunk
        boolean a = true;
        for (int i = 0; i < ships.size() && a; ++i) {
            for (int j = 0; j < ships.get(i).size() && a; ++j) {
                a &= ships.get(i).get(j).isHit();
            }
        }
        if (a) {
            result = FINISHED;
        }
        return result;
    }
}
