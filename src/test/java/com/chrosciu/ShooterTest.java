package com.chrosciu;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.chrosciu.Direction.HORIZONTAL;
import static com.chrosciu.Direction.VERTICAL;
import static com.chrosciu.Result.HIT;
import static com.chrosciu.Result.MISSED;
import static com.chrosciu.Result.SUNK;
import static com.chrosciu.Result.FINISHED;
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

    private static final int ONE_FIELD_SHIP_SIZE = 1;
    private static final int TWO_FIELDS_SHIP_SIZE = 2;

    private static final Ship VERTICAL_TWO_FIELDS_SHIP =
            Ship.of(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD, TWO_FIELDS_SHIP_SIZE, VERTICAL);
    private static final Ship ONE_FIELD_SHIP =
            Ship.of(ONE_FIELD_SHIP_FIELD, ONE_FIELD_SHIP_SIZE, HORIZONTAL);
    private static final Ship ANOTHER_ONE_FIELD_SHIP =
            Ship.of(ANOTHER_ONE_FIELD_SHIP_FIELD, ONE_FIELD_SHIP_SIZE, VERTICAL);
    private static final Ship HORIZONTAL_TWO_FIELDS_SHIP =
            Ship.of(HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD, TWO_FIELDS_SHIP_SIZE, HORIZONTAL);
    private static final List<Ship> SHIPS =
            Arrays.asList(VERTICAL_TWO_FIELDS_SHIP, ONE_FIELD_SHIP, ANOTHER_ONE_FIELD_SHIP, HORIZONTAL_TWO_FIELDS_SHIP);

    @Test
    public void shouldProperlyCalculateShootResultForGivenSetofShipsOnBoard() {
        //given
        Shooter shooter = new Shooter(SHIPS);

        //when
        Result result = shooter.takeShot(FIELD_WITHOUT_SHIP);

        //then
        assertEquals(MISSED, result);

        //when
        result = shooter.takeShot(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD);

        //then
        assertEquals(HIT, result);

        //when
        result = shooter.takeShot(ONE_FIELD_SHIP_FIELD);

        //then
        assertEquals(SUNK, result);

        //when
        result = shooter.takeShot(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD);

        //then
        assertEquals(HIT, result);

        //when
        result = shooter.takeShot(VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD);

        //then
        assertEquals(SUNK, result);

        //when
        result = shooter.takeShot(VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD);

        //then
        assertEquals(SUNK, result);

        //when
        result = shooter.takeShot(ANOTHER_ONE_FIELD_SHIP_FIELD);

        //then
        assertEquals(SUNK, result);

        //when
        result = shooter.takeShot(HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD);

        //then
        assertEquals(HIT, result);

        //when
        result = shooter.takeShot(HORIZONTAL_TWO_FIELDS_SHIP_SECOND_FIELD);

        //then
        assertEquals(FINISHED, result);

        //when
        result = shooter.takeShot(HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD);

        //then
        assertEquals(FINISHED, result);

        //when
        result = shooter.takeShot(ANOTHER_FIELD_WITHOUT_SHIP);

        //then
        assertEquals(FINISHED, result);
    }

}
