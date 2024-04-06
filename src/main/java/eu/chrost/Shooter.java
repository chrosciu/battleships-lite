package eu.chrost;

import java.util.ArrayList;
import java.util.List;

import static eu.chrost.Orientation.VERTICAL;
import static eu.chrost.Result.FINISHED;
import static eu.chrost.Result.HIT;
import static eu.chrost.Result.MISSED;
import static eu.chrost.Result.SUNK;

class Shooter {

    private List<Ship> ships = new ArrayList<>();

    public Shooter(List<ShipDefinition> shipDefinitions) {
        for (int i = 0; i < shipDefinitions.size(); ++i) {
            Ship ship = new Ship();
            for (int j = 0; j < shipDefinitions.get(i).getLength(); ++j) {
                if (shipDefinitions.get(i).getOrientation() == VERTICAL) {
                    ship.add(ShipField.of(Field.of(shipDefinitions.get(i).getFirstField().getX(), shipDefinitions.get(i).getFirstField().getY() + j)));
                } else {
                    ship.add(ShipField.of(Field.of(shipDefinitions.get(i).getFirstField().getX() + j, shipDefinitions.get(i).getFirstField().getY())));
                }
            }
            ships.add(ship);
        }
    }

    public Result shoot(Field s) {
        var result = MISSED;
        //iterate through all ships
        for (int i = 0; i < ships.size() && MISSED == result; ++i) {
            //iterate through all ship fields
            for (int j = 0; j < ships.get(i).size() && MISSED == result; ++j) {
                //if any of ship fields is equal to passed field - mark as hit
                if (ships.get(i).get(j).getField().getX() == s.getX() && ships.get(i).get(j).getField().getY() == s.getY()) {
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
