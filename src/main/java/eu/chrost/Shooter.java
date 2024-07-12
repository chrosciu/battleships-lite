package eu.chrost;

import java.util.ArrayList;
import java.util.List;

import static eu.chrost.Result.FINISHED;
import static eu.chrost.Result.HIT;
import static eu.chrost.Result.MISSED;
import static eu.chrost.Result.SUNK;

public class Shooter {

    private List<List<ShipField>> data = new ArrayList<>();

    /**
     * Initialize shooter with given list of ships on board
     *
     * @param input - list of ships. Each ship is described by first field coordinate, length and orientation
     *
     */
    public Shooter(List<Ship> input) {
        for (int i = 0; i < input.size(); ++i) {
            List<ShipField> list = new ArrayList<>();
            for (int j = 0; j < input.get(i).getLength(); ++j) {
                var firstField = input.get(i).getFirstField();
                var orientation = input.get(i).getOrientation();
                var shiftedField = firstField.shift(j, orientation);
                list.add(new ShipField(shiftedField));
            }
            data.add(list);
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
        for (int i = 0; i < data.size() && MISSED == result; ++i) {
            //iterate through all ship fields
            for (int j = 0; j < data.get(i).size() && MISSED == result; ++j) {
                //if any of ship fields is equal to passed field - mark as hit
                if (data.get(i).get(j).getField().equals(field)) {
                    data.get(i).get(j).markAsHit();
                    result = HIT;
                }
            }
            //if ship is hit - check if it is sunk
            if (HIT == result) {
                //iterate through all fields and check if they are all hit
                boolean a = true;
                for (int j = 0; j < data.get(i).size() && a; ++j) {
                    a &= data.get(i).get(j).isHit();
                }
                if (a) {
                    result = SUNK;
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
            result = FINISHED;
        }
        return result;
    }
}
