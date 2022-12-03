package com.chrosciu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.chrosciu.Direction.VERTICAL;
import static com.chrosciu.Result.FINISHED;
import static com.chrosciu.Result.HIT;
import static com.chrosciu.Result.MISSED;

public class Shooter {
    @RequiredArgsConstructor
    @Getter
    private static class FieldWithHitMark {
        private final Field field;
        private boolean hit = false;

        public void markAsHit() {
            hit = true;
        }
    }

    private List<List<FieldWithHitMark>> shipsWithHitMarks = new ArrayList<>();

    public Shooter(List<Ship> ships) {
        for (Ship ship : ships) {
            List<FieldWithHitMark> shipWithHitMarks = new ArrayList<>();
            for (int shift = 0; shift < ship.getLength(); ++shift) {
                Field shiftedField = (VERTICAL == ship.getDirection()) ?
                        Field.of(ship.getFirstField().getX(), ship.getFirstField().getY() + shift) :
                        Field.of(ship.getFirstField().getX() + shift, ship.getFirstField().getY());
                shipWithHitMarks.add(new FieldWithHitMark(shiftedField));
            }
            shipsWithHitMarks.add(shipWithHitMarks);
        }
    }

    public Result shoot(Field s) {
        Result result = MISSED;
        //iterate through all ships
        for (int i = 0; i < shipsWithHitMarks.size() && MISSED == result; ++i) {
            //iterate through all ship fields
            for (int j = 0; j < shipsWithHitMarks.get(i).size() && MISSED == result; ++j) {
                //if any of ship fields is equal to passed field - mark as hit
                if (shipsWithHitMarks.get(i).get(j).getField().getX() == s.getX() && shipsWithHitMarks.get(i).get(j).getField().getY() == s.getY()) {
                    shipsWithHitMarks.get(i).get(j).markAsHit();
                    result = HIT;
                }
            }
            //if ship is hit - check if it is sunk
            if (HIT == result) {
                //iterate through all fields and check if they are all hit
                boolean a = true;
                for (int j = 0; j < shipsWithHitMarks.get(i).size() && a; ++j) {
                    a &= shipsWithHitMarks.get(i).get(j).isHit();
                }
                if (a) {
                    result = Result.SUNK;
                }
            }
        }
        //check if all ships are sunk
        boolean a = true;
        for (int i = 0; i < shipsWithHitMarks.size() && a; ++i) {
            for (int j = 0; j < shipsWithHitMarks.get(i).size() && a; ++j) {
                a &= shipsWithHitMarks.get(i).get(j).isHit();
            }
        }
        if (a) {
            result = FINISHED;
        }
        return result;
    }
}
