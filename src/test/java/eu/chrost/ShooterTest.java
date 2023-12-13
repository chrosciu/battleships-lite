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

    private static final ShipDefinition VERTICAL_TWO_FIELDS_SHIP_DEFINITION =
            ShipDefinition.of(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD, TWO_FIELDS_SHIP_SIZE, VERTICAL);
    private static final ShipDefinition ONE_FIELD_SHIP_DEFINITION =
            ShipDefinition.of(ONE_FIELD_SHIP_FIELD, ONE_FIELD_SHIP_SIZE, HORIZONTAL);
    private static final ShipDefinition ANOTHER_ONE_FIELD_SHIP_DEFINITION =
            ShipDefinition.of(ANOTHER_ONE_FIELD_SHIP_FIELD, ONE_FIELD_SHIP_SIZE, VERTICAL);
    private static final ShipDefinition HORIZONTAL_TWO_FIELDS_SHIP_DEFINITION =
            ShipDefinition.of(HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD, TWO_FIELDS_SHIP_SIZE, HORIZONTAL);

    private static final List<ShipDefinition> BOARD_WITHOUT_SHIP_DEFINITIONS =
            List.of();
    private static final List<ShipDefinition> BOARD_WITH_ONE_SHIP_DEFINITION =
            List.of(VERTICAL_TWO_FIELDS_SHIP_DEFINITION);
    private static final List<ShipDefinition> BOARD_WITH_FOUR_SHIP_DEFINITIONS =
            List.of(VERTICAL_TWO_FIELDS_SHIP_DEFINITION, ONE_FIELD_SHIP_DEFINITION, ANOTHER_ONE_FIELD_SHIP_DEFINITION, HORIZONTAL_TWO_FIELDS_SHIP_DEFINITION);

    @Test
    public void shouldReportFinishedWhenShootingOnBoardWithoutShips() {
        //given
        Shooter shooter = new Shooter(BOARD_WITHOUT_SHIP_DEFINITIONS);

        //when
        Result result = shooter.shoot(FIELD_WITHOUT_SHIP);

        //then
        assertThat(result).isEqualTo(FINISHED);
    }

    @Test
    public void shouldReportProperStatesWhenShootingOnSingleShip() {
        //given
        Shooter shooter = new Shooter(BOARD_WITH_ONE_SHIP_DEFINITION);

        //when
        Result result = shooter.shoot(FIELD_WITHOUT_SHIP);

        //then        
        assertThat(result).isEqualTo(MISSED);

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD);

        //then
        assertThat(result).isEqualTo(HIT);

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD);

        //then
        assertThat(result).isEqualTo(FINISHED);
    }

    @Test
    public void shouldProperlyCalculateShootResultForGivenSetofShipsOnBoard() {
        //given
        Shooter shooter = new Shooter(BOARD_WITH_FOUR_SHIP_DEFINITIONS);

        //when
        Result result = shooter.shoot(FIELD_WITHOUT_SHIP);

        //then
        assertThat(result).isEqualTo(MISSED);

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD);

        //then
        assertThat(result).isEqualTo(HIT);

        //when
        result = shooter.shoot(ONE_FIELD_SHIP_FIELD);

        //then
        assertThat(result).isEqualTo(SUNK);

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD);

        //then
        assertThat(result).isEqualTo(HIT);

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD);

        //then
        assertThat(result).isEqualTo(SUNK);

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD);

        //then
        assertThat(result).isEqualTo(SUNK);

        //when
        result = shooter.shoot(ANOTHER_ONE_FIELD_SHIP_FIELD);

        //then
        assertThat(result).isEqualTo(SUNK);

        //when
        result = shooter.shoot(HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD);

        //then
        assertThat(result).isEqualTo(HIT);

        //when
        result = shooter.shoot(HORIZONTAL_TWO_FIELDS_SHIP_SECOND_FIELD);

        //then
        assertThat(result).isEqualTo(FINISHED);

        //when
        result = shooter.shoot(HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD);

        //then
        assertThat(result).isEqualTo(FINISHED);

        //when
        result = shooter.shoot(ANOTHER_FIELD_WITHOUT_SHIP);

        //then
        assertThat(result).isEqualTo(FINISHED);
    }

}
