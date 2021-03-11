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
        for (Ship ship: ships) {
            List<FieldWithHitMark> shipWithHitMarks = new ArrayList<>();
            for (int shift = 0; shift < ship.getLength(); ++shift) {
                if (ship.isVertical()) {
                    shipWithHitMarks.add(FieldWithHitMark.from(Field.of(ship.getFirstField().getX(), ship.getFirstField().getY() + shift)));
                } else {
                    shipWithHitMarks.add(FieldWithHitMark.from(Field.of(ship.getFirstField().getX() + shift, ship.getFirstField().getY())));
                }
            }
            shipsWitHitMarks.add(shipWithHitMarks);
        }
    }

    public Result shoot(Field field) {
        Result result = MISSED;
        for (int i = 0; i < shipsWitHitMarks.size() && MISSED == result; ++i) {
            List<FieldWithHitMark> shipWithHitMarks = shipsWitHitMarks.get(i);
            for (int j = 0; j < shipWithHitMarks.size() && MISSED == result; ++j) {
                FieldWithHitMark fieldWithHitMark = shipWithHitMarks.get(j);
                if (fieldWithHitMark.getField().equals(field)) {
                    fieldWithHitMark.markAsHit();
                    result = HIT;
                }
            }
            if (HIT == result) {
                boolean allShipFieldsHit = true;
                for (int j = 0; j < shipWithHitMarks.size() && allShipFieldsHit; ++j) {
                    allShipFieldsHit &= shipWithHitMarks.get(j).isHit();
                }
                if (allShipFieldsHit) {
                    result = SUNK;
                }
            }
        }
        boolean allShipsSunk = true;
        for (int i = 0; i < shipsWitHitMarks.size() && allShipsSunk; ++i) {
            List<FieldWithHitMark> shipWithHitMarks = shipsWitHitMarks.get(i);
            for (int j = 0; j < shipWithHitMarks.size() && allShipsSunk; ++j) {
                allShipsSunk &= shipWithHitMarks.get(j).isHit();
            }
        }
        if (allShipsSunk) {
            result = FINISHED;
        }
        return result;
    }
}
