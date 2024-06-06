package eu.chrost;

import eu.chrost.Shooter.Point;
import eu.chrost.Shooter.Ship;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static eu.chrost.Shooter.point;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
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
            Ship.of(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD, TWO_FIELDS_SHIP_SIZE, VERTICAL);
    private static final Ship ONE_FIELD_SHIP =
            Ship.of(ONE_FIELD_SHIP_FIELD, ONE_FIELD_SHIP_SIZE, HORIZONTAL);
    private static final Ship ANOTHER_ONE_FIELD_SHIP =
            Ship.of(ANOTHER_ONE_FIELD_SHIP_FIELD, ONE_FIELD_SHIP_SIZE, VERTICAL);
    private static final Ship HORIZONTAL_TWO_FIELDS_SHIP =
            Ship.of(HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD, TWO_FIELDS_SHIP_SIZE, HORIZONTAL);

    private static final List<Ship> BOARD_WITH_NO_SHIPS =
            List.of();
    private static final List<Ship> BOARD_WITH_SINGLE_TWO_FIELDS_SHIP =
            List.of(VERTICAL_TWO_FIELDS_SHIP);
    private static final List<Ship> BOARD_WITH_MULTIPLE_SHIPS =
            List.of(VERTICAL_TWO_FIELDS_SHIP, ONE_FIELD_SHIP, ANOTHER_ONE_FIELD_SHIP, HORIZONTAL_TWO_FIELDS_SHIP);

    private static final int MISSED = 0;
    private static final int HIT = 1;
    private static final int SUNK = 2;
    private static final int FINISHED = 3;

    @Test
    void A_board_without_ships_returns_finished_state_on_first_shot() {
        //given
        Shooter shooter = new Shooter(BOARD_WITH_NO_SHIPS);

        //when
        int result = shooter.shoot(FIELD_WITHOUT_SHIP).getRank();

        //then
        assertThat(result).isEqualTo(FINISHED);
    }

    @Nested
    class A_board_with_single_two_fields_ship {
        //given
        Shooter shooter = new Shooter(BOARD_WITH_SINGLE_TWO_FIELDS_SHIP);

        @Test
        void returns_missed_status_on_first_shot_on_field_without_ship() {
            //when
            int result = shooter.shoot(FIELD_WITHOUT_SHIP).getRank();

            //then
            assertEquals(MISSED, result);
        }

        @Test
        void returns_hit_and_then_sunk_results_on_shot_on_all_ship_fields() {
            //when
            int result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD).getRank();

            //then
            assertEquals(HIT, result);

            //when
            result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD).getRank();

            //then
            assertEquals(FINISHED, result);
        }
    }

    @Test
    void A_board_with_multiple_ships_returns_proper_statuses_on_all_shots() {
        //given
        Shooter shooter = new Shooter(BOARD_WITH_MULTIPLE_SHIPS);

        //when
        int result = shooter.shoot(FIELD_WITHOUT_SHIP).getRank();

        //then
        assertEquals(MISSED, result);

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD).getRank();

        //then
        assertEquals(HIT, result);

        //when
        result = shooter.shoot(ONE_FIELD_SHIP_FIELD).getRank();

        //then
        assertEquals(SUNK, result);

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD).getRank();

        //then
        assertEquals(HIT, result);

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD).getRank();

        //then
        assertEquals(SUNK, result);

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD).getRank();

        //then
        assertEquals(SUNK, result);

        //when
        result = shooter.shoot(ANOTHER_ONE_FIELD_SHIP_FIELD).getRank();

        //then
        assertEquals(SUNK, result);

        //when
        result = shooter.shoot(HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD).getRank();

        //then
        assertEquals(HIT, result);

        //when
        result = shooter.shoot(HORIZONTAL_TWO_FIELDS_SHIP_SECOND_FIELD).getRank();

        //then
        assertEquals(FINISHED, result);

        //when
        result = shooter.shoot(HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD).getRank();

        //then
        assertEquals(FINISHED, result);

        //when
        result = shooter.shoot(ANOTHER_FIELD_WITHOUT_SHIP).getRank();

        //then
        assertEquals(FINISHED, result);
    }

}

