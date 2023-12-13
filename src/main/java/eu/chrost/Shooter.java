package eu.chrost;

import java.util.ArrayList;
import java.util.List;

import static eu.chrost.Orientation.VERTICAL;
import static eu.chrost.Result.FINISHED;
import static eu.chrost.Result.HIT;
import static eu.chrost.Result.MISSED;
import static eu.chrost.Result.SUNK;

public class Shooter {

    private List<Ship> ships = new ArrayList<>();

    /**
     * Initialize shooter with given list of ships on board
     *
     * @param shipDefinitions - list of ships. Each ship is described by first field coordinate, length and orientation
     *              (true - vertical, false - horizontal)
     */
    public Shooter(List<ShipDefinition> shipDefinitions) {
        for (int i = 0; i < shipDefinitions.size(); ++i) {
            Ship ship = new Ship();
            for (int j = 0; j < shipDefinitions.get(i).getLength(); ++j) {
                if (VERTICAL == shipDefinitions.get(i).getOrientation()) {
                    ship.add(ShipField.of(Field.of(shipDefinitions.get(i).getFirstField().getX(), shipDefinitions.get(i).getFirstField().getY() + j)));
                } else {
                    ship.add(ShipField.of(Field.of(shipDefinitions.get(i).getFirstField().getX() + j, shipDefinitions.get(i).getFirstField().getY())));
                }
            }
            ships.add(ship);
        }
    }

    /**
     * Take shot for given field and return shot result
     *
     * @param field - field coordinates
     */
    public Result shoot(Field field) {
        Result rv = MISSED;
        //iterate through all ships
        for (int i = 0; i < ships.size() && MISSED == rv; ++i) {
            //iterate through all ship fields
            for (int j = 0; j < ships.get(i).size() && MISSED == rv; ++j) {
                //if any of ship fields is equal to passed field - mark as hit
                if (ships.get(i).get(j).getField().getX() == field.getX() && ships.get(i).get(j).getField().getY() == field.getY()) {
                    ships.get(i).get(j).markAsHit();
                    rv = HIT;
                }
            }
            //if ship is hit - check if it is sunk
            if (HIT == rv) {
                //iterate through all fields and check if they are all hit
                boolean a = true;
                for (int j = 0; j < ships.get(i).size() && a; ++j) {
                    a &= ships.get(i).get(j).isHit();
                }
                if (a) {
                    rv = SUNK;
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
            rv = FINISHED;
        }
        return rv;
    }
}
