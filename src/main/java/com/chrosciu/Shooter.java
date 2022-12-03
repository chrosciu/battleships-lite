package com.chrosciu;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.ArrayList;
import java.util.List;

import static com.chrosciu.Direction.VERTICAL;
import static com.chrosciu.Result.FINISHED;
import static com.chrosciu.Result.HIT;
import static com.chrosciu.Result.MISSED;

public class Shooter {

    private List<List<MutablePair<Field, Boolean>>> data = new ArrayList<>();

    public Shooter(List<Ship> ships) {
        for (int i = 0; i < ships.size(); ++i) {
            List<MutablePair<Field, Boolean>> list = new ArrayList<>();
            for (int j = 0; j < ships.get(i).getLength(); ++j) {
                if (VERTICAL == ships.get(i).getDirection()) {
                    list.add(MutablePair.of(Field.of(ships.get(i).getFirstField().getX(), ships.get(i).getFirstField().getY() + j), false));
                } else {
                    list.add(MutablePair.of(Field.of(ships.get(i).getFirstField().getX() + j, ships.get(i).getFirstField().getY()), false));
                }
            }
            data.add(list);
        }
    }

    public Result shoot(Field s) {
        Result result = MISSED;
        //iterate through all ships
        for (int i = 0; i < data.size() && MISSED == result; ++i) {
            //iterate through all ship fields
            for (int j = 0; j < data.get(i).size() && MISSED == result; ++j) {
                //if any of ship fields is equal to passed field - mark as hit
                if (data.get(i).get(j).getLeft().getX() == s.getX() && data.get(i).get(j).getLeft().getY() == s.getY()) {
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
                    result = Result.SUNK;
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
