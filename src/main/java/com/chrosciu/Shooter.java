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
        for (int i = 0; i < shipsWithHitMarks.size() && MISSED == result; ++i) {
            List<FieldWithHitMark> shipWithHitMarks = shipsWithHitMarks.get(i);
            FieldWithHitMark fieldWithHitMark = findGivenFieldInGivenShip(field, shipWithHitMarks);
            if (fieldWithHitMark != null) {
                fieldWithHitMark.markAsHit();
                result = HIT;
                if (isShipSunk(shipWithHitMarks)) {
                    result = SUNK;
                }
            }
        }
        if (areAllShipsSunk()) {
            result = FINISHED;
        }
        return result;
    }

    private FieldWithHitMark findGivenFieldInGivenShip(Field field, List<FieldWithHitMark> shipWithHitMarks) {
        for (FieldWithHitMark fieldWithHitMark: shipWithHitMarks) {
            if (fieldWithHitMark.getField().equals(field)) {
                return fieldWithHitMark;
            }
        }
        return null;
    }

    private static boolean isShipSunk(List<FieldWithHitMark> shipWithHitMarks) {
        boolean isSunk = true;
        for (int j = 0; j < shipWithHitMarks.size() && isSunk; ++j) {
            isSunk &= shipWithHitMarks.get(j).isHit();
        }
        return isSunk;
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
