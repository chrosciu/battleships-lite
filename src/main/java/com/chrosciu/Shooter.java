package com.chrosciu;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.chrosciu.Result.FINISHED;
import static com.chrosciu.Result.HIT;
import static com.chrosciu.Result.MISSED;
import static com.chrosciu.Result.SUNK;

public class Shooter {

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    static class FieldWithHitMark {
        private final Field field;
        private boolean hit = false;

        public static FieldWithHitMark of(Field field) {
            return new FieldWithHitMark(field);
        }

        public void markAsHit() {
            this.hit = true;
        }
    }

    private List<List<FieldWithHitMark>> shipsFieldsWithHitMarks = new ArrayList<>();

    public Shooter(List<Ship> ships) {
        for (int i = 0; i < ships.size(); ++i) {
            List<FieldWithHitMark> list = new ArrayList<>();
            for (int j = 0; j < ships.get(i).getSize(); ++j) {
                list.add(FieldWithHitMark.of(ships.get(i).getFirstField().shift(j, ships.get(i).getOrientation())));
            }
            shipsFieldsWithHitMarks.add(list);
        }
    }

    public Result takeShot(Field field) {
        Result result = MISSED;
        //iterate through all ships
        for (int i = 0; i < shipsFieldsWithHitMarks.size() && MISSED == result; ++i) {
            //iterate through all ship fields
            for (int j = 0; j < shipsFieldsWithHitMarks.get(i).size() && MISSED == result; ++j) {
                //if any of ship fields is equal to passed field - mark as hit
                if (shipsFieldsWithHitMarks.get(i).get(j).getField().equals(field)) {
                    shipsFieldsWithHitMarks.get(i).get(j).markAsHit();
                    result = HIT;
                }
            }
            //if ship is hit - check if it is sunk
            if (HIT == result) {
                //iterate through all fields and check if they are all hit
                boolean a = true;
                for (int j = 0; j < shipsFieldsWithHitMarks.get(i).size() && a; ++j) {
                    a &= shipsFieldsWithHitMarks.get(i).get(j).isHit();
                }
                if (a) {
                    result = SUNK;
                }
            }
        }
        //check if all ships are sunk
        boolean a = true;
        for (int i = 0; i < shipsFieldsWithHitMarks.size() && a; ++i) {
            for (int j = 0; j < shipsFieldsWithHitMarks.get(i).size() && a; ++j) {
                a &= shipsFieldsWithHitMarks.get(i).get(j).isHit();
            }
        }
        if (a) {
            result = FINISHED;
        }
        return result;
    }
}
