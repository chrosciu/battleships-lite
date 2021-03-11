package com.chrosciu;


import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;

import static com.chrosciu.Result.FINISHED;
import static com.chrosciu.Result.HIT;
import static com.chrosciu.Result.MISSED;
import static com.chrosciu.Result.SUNK;

public class Shooter {

    private List<List<MutablePair<Field, Boolean>>> data = new ArrayList<>();

    /**
     * Initialize shooter with given list of ships on board
     *
     * @param input - list of ships. Each ship is described by first field coordinate, length and orientation
     *              (true - vertical, false - horizontal)
     */
    public Shooter(List<Triple<Field, Integer, Boolean>> input) {
        for (int i = 0; i < input.size(); ++i) {
            List<MutablePair<Field, Boolean>> list = new ArrayList<>();
            for (int j = 0; j < input.get(i).getMiddle(); ++j) {
                if (input.get(i).getRight()) {
                    list.add(MutablePair.of(Field.of(input.get(i).getLeft().getX(), input.get(i).getLeft().getY() + j), false));
                } else {
                    list.add(MutablePair.of(Field.of(input.get(i).getLeft().getX() + j, input.get(i).getLeft().getY()), false));
                }
            }
            data.add(list);
        }
    }

    /**
     * Take shot for given field and return shot result
     *
     * @param field - field coordinates
     * @return - shot result: 0 - no hit, 1 - ship hit, 2 - ship sunk, 3 - all ships sunk
     */
    public Result shoot(Field field) {
        Result result = MISSED;
        //iterate through all ships
        for (int i = 0; i < data.size() && MISSED == result; ++i) {
            //iterate through all ship fields
            for (int j = 0; j < data.get(i).size() && MISSED == result; ++j) {
                //if any of ship fields is equal to passed field - mark as hit
                if (data.get(i).get(j).getLeft().getX() == field.getX() && data.get(i).get(j).getLeft().getY() == field.getY()) {
                    data.get(i).get(j).setRight(true);
                    result = HIT;
                }
            }
            //if ship is hit - check if it is sunk
            if (HIT == result) {
                //iterate through all fields and check if they are all hit
                boolean a = true;
                for (int j = 0; j < data.get(i).size() && a; ++j) {
                    a &= data.get(i).get(j).getRight();
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
                a &= data.get(i).get(j).getRight();
            }
        }
        if (a) {
            result = FINISHED;
        }
        return result;
    }
}
