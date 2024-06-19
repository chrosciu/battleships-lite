package eu.chrost;

import java.util.ArrayList;
import java.util.List;

import static eu.chrost.Result.FINISHED;
import static eu.chrost.Result.HIT;
import static eu.chrost.Result.MISSED;
import static eu.chrost.Result.SUNK;

public class Shooter {

    public static class ShipDefinition {
        private Field field;
        private int length;
        private Orientation orientation;

        public static ShipDefinition of(Field field, int length, Orientation orientation) {
            ShipDefinition shipDefinition = new ShipDefinition();
            shipDefinition.field = field;
            shipDefinition.length = length;
            shipDefinition.orientation = orientation;
            return shipDefinition;
        }

        public Field getField() {
            return field;
        }

        public int getLength() {
            return length;
        }

        public Orientation getOrientation() {
            return orientation;
        }

        public void setField(Field field) {
            this.field = field;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public void setOrientation(Orientation orientation) {
            this.orientation = orientation;
        }
    }

    private List<List<ShipField>> ships = new ArrayList<>();

    /**
     * Initialize shooter with given list of ships on board
     *
     * @param input - list of ships. Each ship is described by first field coordinate, length and orientation
     *              (true - vertical, false - horizontal)
     */
    public Shooter(List<ShipDefinition> input) {
        for (int i = 0; i < input.size(); ++i) {
            List<ShipField> list = new ArrayList<>();
            for (int j = 0; j < input.get(i).getLength(); ++j) {
                var firstField = input.get(i).getField();
                var orientation = input.get(i).getOrientation();
                var shiftedField = firstField.shift(j, orientation);
                var shipField = ShipField.of(shiftedField);
                list.add(shipField);
            }
            ships.add(list);
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
