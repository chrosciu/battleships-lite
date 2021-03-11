package com.chrosciu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    private final List<List<FieldWithHitMark>> shipsWitHitMarks;

    public Shooter(List<Ship> ships) {
        shipsWitHitMarks = ships.stream()
                .map(ship -> IntStream.range(0, ship.getLength())
                        .mapToObj(shift -> FieldWithHitMark.from(ship.getFirstField().shiftInDirection(ship.getDirection(), shift)))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public Result takeShot(Field field) {
        Result result = MISSED;
        for (List<FieldWithHitMark> shipWithHitMarks: shipsWitHitMarks) {
            Optional<FieldWithHitMark> fieldWithHitMark = findGivenFieldInGivenShip(field, shipWithHitMarks);
            if (fieldWithHitMark.isPresent()) {
                fieldWithHitMark.get().markAsHit();
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

    private Optional<FieldWithHitMark> findGivenFieldInGivenShip(Field field, List<FieldWithHitMark> shipWithHitMarks) {
        return shipWithHitMarks.stream()
                .filter(fieldWithHitMark -> fieldWithHitMark.getField().equals(field))
                .findFirst();
    }

    private boolean checkIfAllShipFieldsAreHit(List<FieldWithHitMark> shipWithHitMarks) {
        return shipWithHitMarks.stream().allMatch(FieldWithHitMark::isHit);
    }

    private boolean checkIfAllShipsAreSunk() {
        return shipsWitHitMarks.stream().allMatch(this::checkIfAllShipFieldsAreHit);
    }
}
