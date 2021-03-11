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

    public Shooter(List<Ship> ships) {
        for (int i = 0; i < ships.size(); ++i) {
            List<FieldWithHitMark> shipWithHitMarks = new ArrayList<>();
            for (int j = 0; j < ships.get(i).getLength(); ++j) {
                shipWithHitMarks.add(FieldWithHitMark.from(ships.get(i).getFirstField().shiftInDirection(ships.get(i).getDirection(), j)));
            }
            shipsWitHitMarks.add(shipWithHitMarks);
        }
    }

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
                boolean isSunk = true;
                for (int j = 0; j < shipsWitHitMarks.get(i).size() && isSunk; ++j) {
                    isSunk &= shipsWitHitMarks.get(i).get(j).isHit();
                }
                if (isSunk) {
                    result = SUNK;
                }
            }
        }
        //check if all ships are sunk
        boolean isFinished = true;
        for (int i = 0; i < shipsWitHitMarks.size() && isFinished; ++i) {
            for (int j = 0; j < shipsWitHitMarks.get(i).size() && isFinished; ++j) {
                isFinished &= shipsWitHitMarks.get(i).get(j).isHit();
            }
        }
        if (isFinished) {
            result = FINISHED;
        }
        return result;
    }
}
