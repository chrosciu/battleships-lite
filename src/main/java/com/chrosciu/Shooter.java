package com.chrosciu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.chrosciu.Result.FINISHED;
import static com.chrosciu.Result.HIT;
import static com.chrosciu.Result.MISSED;
import static com.chrosciu.Result.SUNK;

public class Shooter {

    @Getter
    @RequiredArgsConstructor(staticName = "from")
    static class FieldWithHitMark {
        private final Field field;
        private boolean hit;

        void markAsHit() {
            this.hit = true;
        }

    }

    private final List<List<FieldWithHitMark>> shipsWitHitMarks = new ArrayList<>();

    /**
     * Initialize shooter with given list of ships on board
     *
     * @param input - list of ships. Each ship is described by first field coordinate, length and orientation
     *              (true - vertical, false - horizontal)
     */
    public Shooter(List<Ship> input) {
        for (int i = 0; i < input.size(); ++i) {
            List<FieldWithHitMark> shipWithHitMarks = new ArrayList<>();
            for (int j = 0; j < input.get(i).getLength(); ++j) {
                if (input.get(i).isVertical()) {
                    shipWithHitMarks.add(FieldWithHitMark.from(Field.of(input.get(i).getFirstField().getX(), input.get(i).getFirstField().getY() + j)));
                } else {
                    shipWithHitMarks.add(FieldWithHitMark.from(Field.of(input.get(i).getFirstField().getX() + j, input.get(i).getFirstField().getY())));
                }
            }
            shipsWitHitMarks.add(shipWithHitMarks);
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
        for (int i = 0; i < shipsWitHitMarks.size() && MISSED == result; ++i) {
            //iterate through all ship fields
            for (int j = 0; j < shipsWitHitMarks.get(i).size() && MISSED == result; ++j) {
                //if any of ship fields is equal to passed field - mark as hit
                if (shipsWitHitMarks.get(i).get(j).getField().equals(field)) {
                    shipsWitHitMarks.get(i).get(j).markAsHit();
                    result = HIT;
                }
            }
            //if ship is hit - check if it is sunk
            if (HIT == result) {
                //iterate through all fields and check if they are all hit
                boolean a = true;
                for (int j = 0; j < shipsWitHitMarks.get(i).size() && a; ++j) {
                    a &= shipsWitHitMarks.get(i).get(j).isHit();
                }
                if (a) {
                    result = SUNK;
                }
            }
        }
        //check if all ships are sunk
        boolean a = true;
        for (int i = 0; i < shipsWitHitMarks.size() && a; ++i) {
            for (int j = 0; j < shipsWitHitMarks.get(i).size() && a; ++j) {
                a &= shipsWitHitMarks.get(i).get(j).isHit();
            }
        }
        if (a) {
            result = FINISHED;
        }
        return result;
    }
}
