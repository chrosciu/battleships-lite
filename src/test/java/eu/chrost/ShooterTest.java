package eu.chrost;

import eu.chrost.Shooter.Point;
import eu.chrost.Shooter.Ship;
import org.junit.jupiter.api.Test;

import java.util.List;

import static eu.chrost.Shooter.point;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShooterTest {
    private static final Point VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD = point(3, 4);
    private static final Point VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD = point(3, 5);

    private static final Point ONE_FIELD_SHIP_FIELD = point(7, 2);

    private static final Point ANOTHER_ONE_FIELD_SHIP_FIELD = point(6, 5);

    private static final Point HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD = point(1, 1);
    private static final Point HORIZONTAL_TWO_FIELDS_SHIP_SECOND_FIELD = point(2, 1);

    private static final Point FIELD_WITHOUT_SHIP = point(1, 2);
    private static final Point ANOTHER_FIELD_WITHOUT_SHIP = point(4, 4);

    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;

    private static final int ONE_FIELD_SHIP_SIZE = 1;
    private static final int TWO_FIELDS_SHIP_SIZE = 2;

    private static final Ship VERTICAL_TWO_FIELDS_SHIP =
            new Ship(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD, TWO_FIELDS_SHIP_SIZE, VERTICAL);
    private static final Ship ONE_FIELD_SHIP =
            new Ship(ONE_FIELD_SHIP_FIELD, ONE_FIELD_SHIP_SIZE, HORIZONTAL);
    private static final Ship ANOTHER_ONE_FIELD_SHIP =
            new Ship(ANOTHER_ONE_FIELD_SHIP_FIELD, ONE_FIELD_SHIP_SIZE, VERTICAL);
    private static final Ship HORIZONTAL_TWO_FIELDS_SHIP =
            new Ship(HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD, TWO_FIELDS_SHIP_SIZE, HORIZONTAL);

    private static final List<Ship> NO_SHIPS =
            List.of();
    private static final List<Ship> SINGLE_SHIP =
            List.of(VERTICAL_TWO_FIELDS_SHIP);
    private static final List<Ship> FOUR_SHIPS =
            List.of(VERTICAL_TWO_FIELDS_SHIP, ONE_FIELD_SHIP, ANOTHER_ONE_FIELD_SHIP, HORIZONTAL_TWO_FIELDS_SHIP);

    private static final int MISSED = 0;
    private static final int HIT = 1;
    private static final int SUNK = 2;
    private static final int FINISHED = 3;

    @Test
    void whenShootingOnBoardWithNoShipsFinishedResultShouldBeReported() {
        //given
        Shooter board = new Shooter(NO_SHIPS);

        //when
        int result = board.shoot(FIELD_WITHOUT_SHIP);

        //then
        assertThat(result).isEqualTo(FINISHED);
    }

    @Test
    public void whenShootingOnBoardWithSingleShipProperStatesShouldBeReported() {
        //given
        Shooter board = new Shooter(SINGLE_SHIP);

        //when
        int result = board.shoot(FIELD_WITHOUT_SHIP);

        //then
        assertEquals(MISSED, result);

        //when
        result = board.shoot(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD);

        //then
        assertEquals(HIT, result);

        //when
        result = board.shoot(VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD);

        //then
        assertEquals(FINISHED, result);
    }

    @Test
    public void whenShootingOnBoardWithMultipleShipsProperStatesShouldBeReported() {
        //given
        Shooter board = new Shooter(FOUR_SHIPS);

        //when
        int result = board.shoot(FIELD_WITHOUT_SHIP);

        //then
        assertEquals(MISSED, result);

        //when
        result = board.shoot(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD);

        //then
        assertEquals(HIT, result);

        //when
        result = board.shoot(ONE_FIELD_SHIP_FIELD);

        //then
        assertEquals(SUNK, result);

        //when
        result = board.shoot(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD);

        //then
        assertEquals(HIT, result);

        //when
        result = board.shoot(VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD);

        //then
        assertEquals(SUNK, result);

        //when
        result = board.shoot(VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD);

        //then
        assertEquals(SUNK, result);

        //when
        result = board.shoot(ANOTHER_ONE_FIELD_SHIP_FIELD);

        //then
        assertEquals(SUNK, result);

        //when
        result = board.shoot(HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD);

        //then
        assertEquals(HIT, result);

        //when
        result = board.shoot(HORIZONTAL_TWO_FIELDS_SHIP_SECOND_FIELD);

        //then
        assertEquals(FINISHED, result);

        //when
        result = board.shoot(HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD);

        //then
        assertEquals(FINISHED, result);

        //when
        result = board.shoot(ANOTHER_FIELD_WITHOUT_SHIP);

        //then
        assertEquals(FINISHED, result);
    }
}

