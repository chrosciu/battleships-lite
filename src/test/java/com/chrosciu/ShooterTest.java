package com.chrosciu;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShooterTest {
    private static final Field VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD = Field.of(3, 4);
    private static final Field VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD = Field.of(3, 5);

    private static final Field ONE_FIELD_SHIP_FIELD = Field.of(7, 2);

    private static final Field ANOTHER_ONE_FIELD_SHIP_FIELD = Field.of(6, 5);

    private static final Field HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD = Field.of(1, 1);
    private static final Field HORIZONTAL_TWO_FIELDS_SHIP_SECOND_FIELD = Field.of(2, 1);

    private static final Field FIELD_WITHOUT_SHIP = Field.of(1, 2);
    private static final Field ANOTHER_FIELD_WITHOUT_SHIP = Field.of(4, 4);

    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;

    private static final int ONE_FIELD_SHIP_SIZE = 1;
    private static final int TWO_FIELDS_SHIP_SIZE = 2;

    private static final Triple<Field, Integer, Boolean> VERTICAL_TWO_FIELDS_SHIP =
            Triple.of(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD, TWO_FIELDS_SHIP_SIZE, VERTICAL);
    private static final Triple<Field, Integer, Boolean> ONE_FIELD_SHIP =
            Triple.of(ONE_FIELD_SHIP_FIELD, ONE_FIELD_SHIP_SIZE, HORIZONTAL);
    private static final Triple<Field, Integer, Boolean> ANOTHER_ONE_FIELD_SHIP =
            Triple.of(ANOTHER_ONE_FIELD_SHIP_FIELD, ONE_FIELD_SHIP_SIZE, VERTICAL);
    private static final Triple<Field, Integer, Boolean> HORIZONTAL_TWO_FIELDS_SHIP =
            Triple.of(HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD, TWO_FIELDS_SHIP_SIZE, HORIZONTAL);
    private static final List<Triple<Field, Integer, Boolean>> SHIPS =
            Arrays.asList(VERTICAL_TWO_FIELDS_SHIP, ONE_FIELD_SHIP, ANOTHER_ONE_FIELD_SHIP, HORIZONTAL_TWO_FIELDS_SHIP);

    private static final int MISSED = 0;
    private static final int HIT = 1;
    private static final int SUNK = 2;
    private static final int FINISHED = 3;

    @Test
    public void shouldProperlyCalculateShootResultForGivenSetofShipsOnBoard() {
        //given
        Shooter shooter = new Shooter(SHIPS);

        //when
        int result = shooter.shoot(FIELD_WITHOUT_SHIP);

        //then
        assertEquals(MISSED, result);

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD);

        //then
        assertEquals(HIT, result);

        //when
        result = shooter.shoot(ONE_FIELD_SHIP_FIELD);

        //then
        assertEquals(SUNK, result);

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD);

        //then
        assertEquals(HIT, result);

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD);

        //then
        assertEquals(SUNK, result);

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD);

        //then
        assertEquals(SUNK, result);

        //when
        result = shooter.shoot(ANOTHER_ONE_FIELD_SHIP_FIELD);

        //then
        assertEquals(SUNK, result);

        //when
        result = shooter.shoot(HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD);

        //then
        assertEquals(HIT, result);

        //when
        result = shooter.shoot(HORIZONTAL_TWO_FIELDS_SHIP_SECOND_FIELD);

        //then
        assertEquals(FINISHED, result);

        //when
        result = shooter.shoot(HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD);

        //then
        assertEquals(FINISHED, result);

        //when
        result = shooter.shoot(ANOTHER_FIELD_WITHOUT_SHIP);

        //then
        assertEquals(FINISHED, result);
    }

}
