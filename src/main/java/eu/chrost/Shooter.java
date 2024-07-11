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
        public Field p;
        public boolean h;

        public static PointH of(Field p, boolean h) {
            PointH pointH = new PointH();
            pointH.p = p;
            pointH.h = h;
            return pointH;
        }
    }

    public static class Ship {
        private Field p;
        private int l;
        private Orientation orientation;

        public static Ship of(Field p, int l, Orientation orientation) {
            Ship ship = new Ship();
            ship.p = p;
            ship.l = l;
            ship.orientation = orientation;
            return ship;
        }

        public Field getP() {
            return p;
        }

        public int getL() {
            return l;
        }

        public Orientation getOrientation() {
            return orientation;
        }

    }

    private List<List<PointH>> data = new ArrayList<>();

    /**
     * Initialize shooter with given list of ships on board
     *
     * @param input - list of ships. Each ship is described by first field coordinate, length and orientation
     *
     */
    public Shooter(List<Ship> input) {
        for (int i = 0; i < input.size(); ++i) {
            List<PointH> list = new ArrayList<>();
            for (int j = 0; j < input.get(i).getL(); ++j) {
                if (input.get(i).getOrientation() == VERTICAL) {
                    list.add(PointH.of(new Field(input.get(i).getP().x(), input.get(i).getP().y() + j), false));
                } else {
                    list.add(PointH.of(new Field(input.get(i).getP().x() + j, input.get(i).getP().y()), false));
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
                if (data.get(i).get(j).p.x()== field.x() && data.get(i).get(j).p.y() == field.y()) {
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
