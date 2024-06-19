package eu.chrost;

import java.util.ArrayList;
import java.util.List;

import static eu.chrost.Orientation.VERTICAL;
import static eu.chrost.Result.FINISHED;
import static eu.chrost.Result.HIT;
import static eu.chrost.Result.MISSED;
import static eu.chrost.Result.SUNK;

public class Shooter {

    public static class PointH {
        public Field field;
        public boolean h;

        public static PointH of(Field field, boolean h) {
            PointH pointH = new PointH();
            pointH.field = field;
            pointH.h = h;
            return pointH;
        }
    }

    public static class Ship {
        private Field field;
        private int length;
        private Orientation orientation;

        public static Ship of(Field field, int length, Orientation orientation) {
            Ship ship = new Ship();
            ship.field = field;
            ship.length = length;
            ship.orientation = orientation;
            return ship;
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

    private List<List<PointH>> data = new ArrayList<>();

    /**
     * Initialize shooter with given list of ships on board
     *
     * @param input - list of ships. Each ship is described by first field coordinate, length and orientation
     *              (true - vertical, false - horizontal)
     */
    public Shooter(List<Ship> input) {
        for (int i = 0; i < input.size(); ++i) {
            List<PointH> list = new ArrayList<>();
            for (int j = 0; j < input.get(i).getLength(); ++j) {
                if (input.get(i).getOrientation() == VERTICAL) {
                    list.add(PointH.of(Field.of(input.get(i).getField().getX(), input.get(i).getField().getY() + j), false));
                } else {
                    list.add(PointH.of(Field.of(input.get(i).getField().getX() + j, input.get(i).getField().getY()), false));
                }
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
                if (data.get(i).get(j).field.equals(field)) {
                    data.get(i).get(j).h = true;
                    result = HIT;
                }
            }
            //if ship is hit - check if it is sunk
            if (HIT == result) {
                //iterate through all fields and check if they are all hit
                boolean a = true;
                for (int j = 0; j < data.get(i).size() && a; ++j) {
                    a &= data.get(i).get(j).h;
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
                a &= data.get(i).get(j).h;
            }
        }
        if (a) {
            result = FINISHED;
        }
        return result;
    }
}
