package com.chrosciu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
                shipWithHitMarks.add(new FieldWithHitMark(ship.getFirstField().shiftInDirection(shift, ship.getDirection())));
            }
            shipsWithHitMarks.add(shipWithHitMarks);
        }
    }

    public Result shoot(Field field) {
        Result result = MISSED;
        //iterate through all ships
        for (int i = 0; i < shipsWithHitMarks.size() && MISSED == result; ++i) {
            //iterate through all ship fields
            List<FieldWithHitMark> shipWithHitMarks = shipsWithHitMarks.get(i);
            for (int j = 0; j < shipWithHitMarks.size() && MISSED == result; ++j) {
                //if any of ship fields is equal to passed field - mark as hit
                if (shipWithHitMarks.get(j).getField().equals(field)) {
                    shipWithHitMarks.get(j).markAsHit();
                    result = HIT;
                }
            }
            //if ship is hit - check if it is sunk
            if (HIT == result) {
                //iterate through all fields and check if they are all hit
                boolean isSunk = true;
                for (int j = 0; j < shipWithHitMarks.size() && isSunk; ++j) {
                    isSunk &= shipWithHitMarks.get(j).isHit();
                }
                if (isSunk) {
                    result = Result.SUNK;
                }
            }
        }
        if (areAllShipsSunk()) {
            result = FINISHED;
        }
        return result;
    }

    private boolean areAllShipsSunk() {
        boolean allShipsSunk = true;
        for (int i = 0; i < shipsWithHitMarks.size() && allShipsSunk; ++i) {
            List<FieldWithHitMark> shipWithHitMarks = shipsWithHitMarks.get(i);
            for (int j = 0; j < shipWithHitMarks.size() && allShipsSunk; ++j) {
                allShipsSunk &= shipWithHitMarks.get(j).isHit();
            }
        }
        return allShipsSunk;
    }
}
