package eu.chrost;

import java.util.ArrayList;
import java.util.List;

public class Shooter {

    private final List<List<ShipField>> ships;

    /**
     * Initialize shooter with given list of ships on board
     *
     * @param shipDefinitions - list of ships. Each ship is described by first field coordinate, length and orientation
     *              (true - vertical, false - horizontal)
     */
    public Shooter(List<ShipDefinition> shipDefinitions) {
        ships = new ArrayList<>();
        for (int i = 0; i < shipDefinitions.size(); ++i) {
            List<ShipField> list = new ArrayList<>();
            for (int j = 0; j < shipDefinitions.get(i).getLength(); ++j) {
                if (shipDefinitions.get(i).isVertical()) {
                    list.add(new ShipField(new Field(shipDefinitions.get(i).getFirstField().getX(), shipDefinitions.get(i).getFirstField().getY() + j)));
                } else {
                    list.add(new ShipField(new Field(shipDefinitions.get(i).getFirstField().getX() + j, shipDefinitions.get(i).getFirstField().getY())));
                }
            }
            ships.add(list);
        }
    }

    /**
     * Take shot for given field and return shot result
     *
     * @param s - field coordinates
     * @return - shot result: 0 - no hit, 1 - ship hit, 2 - ship sunk, 3 - all ships sunk
     */
    public int shoot(Field s) {
        int rv = 0;
        //iterate through all ships
        for (int i = 0; i < ships.size() && 0 == rv; ++i) {
            //iterate through all ship fields
            for (int j = 0; j < ships.get(i).size() && 0 == rv; ++j) {
                //if any of ship fields is equal to passed field - mark as hit
                if (ships.get(i).get(j).getField().getX() == s.getX() && ships.get(i).get(j).getField().getY() == s.getY()) {
                    ships.get(i).get(j).markAsHit();
                    rv = 1;
                }
            }
            //if ship is hit - check if it is sunk
            if (1 == rv) {
                //iterate through all fields and check if they are all hit
                boolean a = true;
                for (int j = 0; j < ships.get(i).size() && a; ++j) {
                    a &= ships.get(i).get(j).isHit();
                }
                if (a) {
                    rv = 2;
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
            rv = 3;
        }
        return rv;
    }
}
