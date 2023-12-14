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
        private Point p;
        private int l;
        private boolean vertical;

        public static Ship of(Point p, int l, boolean vertical) {
            Ship ship = new Ship();
            ship.p = p;
            ship.l = l;
            ship.vertical = vertical;
            return ship;
        }

        public Point getP() {
            return p;
        }

        public int getL() {
            return l;
        }

        public boolean isVertical() {
            return vertical;
        }

        public void setP(Point p) {
            this.p = p;
        }

        public void setL(int l) {
            this.l = l;
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
            for (int j = 0; j < input.get(i).getL(); ++j) {
                if (input.get(i).isVertical()) {
                    list.add(PointH.of(point(input.get(i).getP().x, input.get(i).getP().y + j), false));
                } else {
                    list.add(PointH.of(point(input.get(i).getP().x + j, input.get(i).getP().y), false));
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
