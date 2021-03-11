package com.chrosciu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.chrosciu.Direction.VERTICAL;
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
                if (VERTICAL.equals(ship.getDirection())) {
                    shipWithHitMarks.add(FieldWithHitMark.from(Field.of(ship.getFirstField().getX(), ship.getFirstField().getY() + shift)));
                } else {
                    shipWithHitMarks.add(FieldWithHitMark.from(Field.of(ship.getFirstField().getX() + shift, ship.getFirstField().getY())));
                }
            }
            shipsWitHitMarks.add(shipWithHitMarks);
        }
    }

    public Result takeShot(Field field) {
        Result result = MISSED;
        for (List<FieldWithHitMark> shipWithHitMarks: shipsWitHitMarks) {
            FieldWithHitMark fieldWithHitMark = findGivenFieldInGivenShip(field, shipWithHitMarks);
            if (fieldWithHitMark != null) {
                fieldWithHitMark.markAsHit();
                result = HIT;
                if (checkIfAllShipFieldsAreHit(shipWithHitMarks)) {
                    result = SUNK;
                }
                break;
            }
        }
        if (checkIfAllShipsAreSunk()) {
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

    private boolean checkIfAllShipFieldsAreHit(List<FieldWithHitMark> shipWithHitMarks) {
        boolean allShipFieldsHit = true;
        for (int j = 0; j < shipWithHitMarks.size() && allShipFieldsHit; ++j) {
            allShipFieldsHit &= shipWithHitMarks.get(j).isHit();
        }
        return allShipFieldsHit;
    }

    private boolean checkIfAllShipsAreSunk() {
        boolean allShipsSunk = true;
        for (int i = 0; i < shipsWitHitMarks.size() && allShipsSunk; ++i) {
            List<FieldWithHitMark> shipWithHitMarks = shipsWitHitMarks.get(i);
            for (int j = 0; j < shipWithHitMarks.size() && allShipsSunk; ++j) {
                allShipsSunk &= shipWithHitMarks.get(j).isHit();
            }
        }
        return allShipsSunk;
    }
}
