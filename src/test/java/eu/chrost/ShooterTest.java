package eu.chrost;

import org.junit.jupiter.api.Test;

import java.util.List;

import static eu.chrost.Field.of;
import static eu.chrost.Orientation.HORIZONTAL;
import static eu.chrost.Orientation.VERTICAL;
import static eu.chrost.Result.FINISHED;
import static eu.chrost.Result.HIT;
import static eu.chrost.Result.MISSED;
import static eu.chrost.Result.SUNK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShooterTest {
    private static final Field VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD = of(3, 4);
    private static final Field VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD = of(3, 5);

    private static final Field ONE_FIELD_SHIP_FIELD = of(7, 2);

    private static final Field ANOTHER_ONE_FIELD_SHIP_FIELD = of(6, 5);

    private static final Field HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD = of(1, 1);
    private static final Field HORIZONTAL_TWO_FIELDS_SHIP_SECOND_FIELD = of(2, 1);

    private static final Field FIELD_WITHOUT_SHIP = of(1, 2);
    private static final Field ANOTHER_FIELD_WITHOUT_SHIP = of(4, 4);

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

    private static final List<Ship> BOARD_WITHOUT_SHIPS =
            List.of();
    private static final List<Ship> BOARD_WITH_ONE_SHIP =
            List.of(VERTICAL_TWO_FIELDS_SHIP);
    private static final List<Ship> BOARD_WITH_FOUR_SHIPS =
            List.of(VERTICAL_TWO_FIELDS_SHIP, ONE_FIELD_SHIP, ANOTHER_ONE_FIELD_SHIP, HORIZONTAL_TWO_FIELDS_SHIP);

    @Test
    public void shouldReportFinishedWhenShootingOnBoardWithoutShips() {
        //given
        Shooter shooter = new Shooter(BOARD_WITHOUT_SHIPS);

        //when
        Result result = shooter.shoot(FIELD_WITHOUT_SHIP);

        //then
        assertThat(result).isEqualTo(FINISHED);
    }

    @Test
    public void shouldReportProperStatesWhenShootingOnSingleShip() {
        //given
        Shooter shooter = new Shooter(BOARD_WITH_ONE_SHIP);

        //when
        Result result = shooter.shoot(FIELD_WITHOUT_SHIP);

        //then
        assertEquals(MISSED, result);

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD);

        //then
        assertEquals(HIT, result);

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD);

        //then
        assertEquals(FINISHED, result);
    }

    @Test
    public void shouldProperlyCalculateShootResultForGivenSetofShipsOnBoard() {
        //given
        Shooter shooter = new Shooter(BOARD_WITH_FOUR_SHIPS);

        //when
        Result result = shooter.shoot(FIELD_WITHOUT_SHIP);

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
