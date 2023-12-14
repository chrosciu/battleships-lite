package eu.chrost;

import java.util.ArrayList;
import java.util.List;

public class Shooter {

    public static class PointH {
        public Field field;
        public boolean hit;

        public static PointH of(Field p, boolean hit) {
            PointH pointH = new PointH();
            pointH.field = p;
            pointH.hit = hit;
            return pointH;
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
                if (input.get(i).isVertical()) {
                    list.add(PointH.of(new Field(input.get(i).getFirstField().getX(), input.get(i).getFirstField().getY() + j), false));
                } else {
                    list.add(PointH.of(new Field(input.get(i).getFirstField().getX() + j, input.get(i).getFirstField().getY()), false));
                }
            }
            data.add(list);
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
        for (int i = 0; i < data.size() && 0 == rv; ++i) {
            //iterate through all ship fields
            for (int j = 0; j < data.get(i).size() && 0 == rv; ++j) {
                //if any of ship fields is equal to passed field - mark as hit
                if (data.get(i).get(j).field.getX() == s.getX() && data.get(i).get(j).field.getY() == s.getY()) {
                    data.get(i).get(j).hit = true;
                    rv = 1;
                }
            }
            //if ship is hit - check if it is sunk
            if (1 == rv) {
                //iterate through all fields and check if they are all hit
                boolean a = true;
                for (int j = 0; j < data.get(i).size() && a; ++j) {
                    a &= data.get(i).get(j).hit;
                }
                if (a) {
                    rv = 2;
                }
            }
        }
        //check if all ships are sunk
        boolean a = true;
        for (int i = 0; i < data.size() && a; ++i) {
            for (int j = 0; j < data.get(i).size() && a; ++j) {
                a &= data.get(i).get(j).hit;
            }
        }
        if (a) {
            rv = 3;
        }
        return rv;
    }
}
