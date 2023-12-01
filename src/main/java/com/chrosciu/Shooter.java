package com.chrosciu;

import java.util.ArrayList;
import java.util.List;

import static com.chrosciu.Direction.VERTICAL;
import static com.chrosciu.Result.*;

public class Shooter {

    private List<List<FieldWithHitMark>> data = new ArrayList<>();

    public Shooter(List<Ship> ships) {
        for (int i = 0; i < ships.size(); ++i) {
            List<FieldWithHitMark> list = new ArrayList<>();
            for (int j = 0; j < ships.get(i).getLength(); ++j) {
                if (VERTICAL == ships.get(i).getDirection()) {
                    list.add(FieldWithHitMark.of(Field.of(ships.get(i).getFirstField().getX(), ships.get(i).getFirstField().getY() + j)));
                } else {
                    list.add(FieldWithHitMark.of(Field.of(ships.get(i).getFirstField().getX() + j, ships.get(i).getFirstField().getY())));
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
    public Result shoot(Field s) {
        Result result = MISSED;
        //iterate through all ships
        for (int i = 0; i < data.size() && MISSED == result; ++i) {
            //iterate through all ship fields
            for (int j = 0; j < data.get(i).size() && MISSED == result; ++j) {
                //if any of ship fields is equal to passed field - mark as hit
                if (data.get(i).get(j).getField().getX() == s.getX() && data.get(i).get(j).getField().getY() == s.getY()) {
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
