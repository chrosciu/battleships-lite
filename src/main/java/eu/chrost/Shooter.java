package eu.chrost;

import java.util.ArrayList;
import java.util.List;

public class Shooter {

    public static class Point {
        public int x;
        public int y;
    }

    public static Point point(int x, int y) {
        Point point = new Point();
        point.x = x;
        point.y = y;
        return point;
    }

    public static class PointH {
        public Point p;
        public boolean h;

        public static PointH of(Point p, boolean h) {
            PointH pointH = new PointH();
            pointH.p = p;
            pointH.h = h;
            return pointH;
        }
    }

    public static class Ship {
        private Point firstField;
        private int length;
        private boolean vertical;

        public static Ship of(Point firstField, int length, boolean vertical) {
            Ship ship = new Ship();
            ship.firstField = firstField;
            ship.length = length;
            ship.vertical = vertical;
            return ship;
        }

        public Point getFirstField() {
            return firstField;
        }

        public int getLength() {
            return length;
        }

        public boolean isVertical() {
            return vertical;
        }

        public void setFirstField(Point firstField) {
            this.firstField = firstField;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public void setVertical(boolean vertical) {
            this.vertical = vertical;
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
                    list.add(PointH.of(point(input.get(i).getFirstField().x, input.get(i).getFirstField().y + j), false));
                } else {
                    list.add(PointH.of(point(input.get(i).getFirstField().x + j, input.get(i).getFirstField().y), false));
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
    public int shoot(Point s) {
        int rv = 0;
        //iterate through all ships
        for (int i = 0; i < data.size() && 0 == rv; ++i) {
            //iterate through all ship fields
            for (int j = 0; j < data.get(i).size() && 0 == rv; ++j) {
                //if any of ship fields is equal to passed field - mark as hit
                if (data.get(i).get(j).p.x == s.x && data.get(i).get(j).p.y == s.y) {
                    data.get(i).get(j).h = true;
                    rv = 1;
                }
            }
            //if ship is hit - check if it is sunk
            if (1 == rv) {
                //iterate through all fields and check if they are all hit
                boolean a = true;
                for (int j = 0; j < data.get(i).size() && a; ++j) {
                    a &= data.get(i).get(j).h;
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
                a &= data.get(i).get(j).h;
            }
        }
        if (a) {
            rv = 3;
        }
        return rv;
    }
}
