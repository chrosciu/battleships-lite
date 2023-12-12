package eu.chrost;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;

import static eu.chrost.Orientation.VERTICAL;
import static eu.chrost.Result.FINISHED;
import static eu.chrost.Result.HIT;
import static eu.chrost.Result.MISSED;
import static eu.chrost.Result.SUNK;

public class Shooter {

    private List<List<MutablePair<Point, Boolean>>> data = new ArrayList<>();

    /**
     * Initialize shooter with given list of ships on board
     *
     * @param input - list of ships. Each ship is described by first field coordinate, length and orientation
     *              (true - vertical, false - horizontal)
     */
    public Shooter(List<Triple<Point, Integer, Orientation>> input) {
        for (int i = 0; i < input.size(); ++i) {
            List<MutablePair<Point, Boolean>> list = new ArrayList<>();
            for (int j = 0; j < input.get(i).getMiddle(); ++j) {
                if (VERTICAL == input.get(i).getRight()) {
                    list.add(MutablePair.of(Point.point(input.get(i).getLeft().getX(), input.get(i).getLeft().getY() + j), false));
                } else {
                    list.add(MutablePair.of(Point.point(input.get(i).getLeft().getX() + j, input.get(i).getLeft().getY()), false));
                }
            }
            data.add(list);
        }
    }

    /**
     * Take shot for given field and return shot result
     *
     * @param s - field coordinates
     */
    public Result shoot(Point s) {
        Result rv = MISSED;
        //iterate through all ships
        for (int i = 0; i < data.size() && MISSED == rv; ++i) {
            //iterate through all ship fields
            for (int j = 0; j < data.get(i).size() && MISSED == rv; ++j) {
                //if any of ship fields is equal to passed field - mark as hit
                if (data.get(i).get(j).getLeft().getX() == s.getX() && data.get(i).get(j).getLeft().getY() == s.getY()) {
                    data.get(i).get(j).setRight(true);
                    rv = HIT;
                }
            }
            //if ship is hit - check if it is sunk
            if (HIT == rv) {
                //iterate through all fields and check if they are all hit
                boolean a = true;
                for (int j = 0; j < data.get(i).size() && a; ++j) {
                    a &= data.get(i).get(j).getRight();
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
                a &= data.get(i).get(j).getRight();
            }
        }
        if (a) {
            rv = FINISHED;
        }
        return rv;
    }
}
