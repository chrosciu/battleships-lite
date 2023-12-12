package eu.chrost;

import java.util.ArrayList;
import java.util.List;

import static eu.chrost.Orientation.VERTICAL;
import static eu.chrost.Result.FINISHED;
import static eu.chrost.Result.HIT;
import static eu.chrost.Result.MISSED;
import static eu.chrost.Result.SUNK;

public class Shooter {

    private List<List<ShipField>> data = new ArrayList<>();

    /**
     * Initialize shooter with given list of ships on board
     *
     * @param ships - list of ships. Each ship is described by first field coordinate, length and orientation
     *              (true - vertical, false - horizontal)
     */
    public Shooter(List<Ship> ships) {
        for (int i = 0; i < ships.size(); ++i) {
            List<ShipField> list = new ArrayList<>();
            for (int j = 0; j < ships.get(i).getLength(); ++j) {
                if (VERTICAL == ships.get(i).getOrientation()) {
                    list.add(ShipField.of(Field.of(ships.get(i).getFirstField().getX(), ships.get(i).getFirstField().getY() + j)));
                } else {
                    list.add(ShipField.of(Field.of(ships.get(i).getFirstField().getX() + j, ships.get(i).getFirstField().getY())));
                }
            }
            data.add(list);
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
        for (int i = 0; i < data.size() && MISSED == rv; ++i) {
            //iterate through all ship fields
            for (int j = 0; j < data.get(i).size() && MISSED == rv; ++j) {
                //if any of ship fields is equal to passed field - mark as hit
                if (data.get(i).get(j).getField().getX() == field.getX() && data.get(i).get(j).getField().getY() == field.getY()) {
                    data.get(i).get(j).markAsHit();
                    rv = HIT;
                }
            }
            //if ship is hit - check if it is sunk
            if (HIT == rv) {
                //iterate through all fields and check if they are all hit
                boolean a = true;
                for (int j = 0; j < data.get(i).size() && a; ++j) {
                    a &= data.get(i).get(j).isHit();
                }
                if (a) {
                    rv = SUNK;
                }
            }
        }
        //check if all ships are sunk
        boolean a = true;
        for (int i = 0; i < data.size() && a; ++i) {
            for (int j = 0; j < data.get(i).size() && a; ++j) {
                a &= data.get(i).get(j).isHit();
            }
        }
        if (a) {
            rv = FINISHED;
        }
        return rv;
    }
}
