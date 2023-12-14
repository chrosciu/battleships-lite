package eu.chrost;

import java.util.ArrayList;
import java.util.List;

import static eu.chrost.Result.*;

public class Board {

    private final List<List<ShipField>> ships;

    /**
     * Initialize shooter with given list of ships on board
     *
     * @param shipDefinitions - list of ships. Each ship is described by first field coordinate, length and orientation
     *              (true - vertical, false - horizontal)
     */
    public Board(List<ShipDefinition> shipDefinitions) {
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
    public Result shoot(Field s) {
        Result result = MISSED;
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
