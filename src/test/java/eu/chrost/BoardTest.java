package eu.chrost;

import org.junit.jupiter.api.Test;

import java.util.List;

import static eu.chrost.Orientation.*;
import static eu.chrost.Result.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {
    private static final Field VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD = new Field(3, 4);
    private static final Field VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD = new Field(3, 5);

    private static final Field ONE_FIELD_SHIP_FIELD = new Field(7, 2);

    private static final Field ANOTHER_ONE_FIELD_SHIP_FIELD = new Field(6, 5);

    private static final Field HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD = new Field(1, 1);
    private static final Field HORIZONTAL_TWO_FIELDS_SHIP_SECOND_FIELD = new Field(2, 1);

    private static final Field FIELD_WITHOUT_SHIP = new Field(1, 2);
    private static final Field ANOTHER_FIELD_WITHOUT_SHIP = new Field(4, 4);

    private static final int ONE_FIELD_SHIP_SIZE = 1;
    private static final int TWO_FIELDS_SHIP_SIZE = 2;

    private static final ShipDefinition VERTICAL_TWO_FIELDS_SHIP_DEFINITION =
            new ShipDefinition(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD, TWO_FIELDS_SHIP_SIZE, VERTICAL);
    private static final ShipDefinition ONE_FIELD_SHIP_DEFINITION =
            new ShipDefinition(ONE_FIELD_SHIP_FIELD, ONE_FIELD_SHIP_SIZE, HORIZONTAL);
    private static final ShipDefinition ANOTHER_ONE_FIELD_SHIP_DEFINITION =
            new ShipDefinition(ANOTHER_ONE_FIELD_SHIP_FIELD, ONE_FIELD_SHIP_SIZE, VERTICAL);
    private static final ShipDefinition HORIZONTAL_TWO_FIELDS_SHIP_DEFINITION =
            new ShipDefinition(HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD, TWO_FIELDS_SHIP_SIZE, HORIZONTAL);

    private static final List<ShipDefinition> NO_SHIP_DEFINITIONS =
            List.of();
    private static final List<ShipDefinition> SINGLE_SHIP_DEFINITION =
            List.of(VERTICAL_TWO_FIELDS_SHIP_DEFINITION);
    private static final List<ShipDefinition> FOUR_SHIP_DEFINITIONS =
            List.of(VERTICAL_TWO_FIELDS_SHIP_DEFINITION, ONE_FIELD_SHIP_DEFINITION, ANOTHER_ONE_FIELD_SHIP_DEFINITION, HORIZONTAL_TWO_FIELDS_SHIP_DEFINITION);

    @Test
    void whenShootingOnBoardWithNoShipsFinishedResultShouldBeReported() {
        //given
        Board board = new Board(NO_SHIP_DEFINITIONS);

        //when
        Result result = board.shoot(FIELD_WITHOUT_SHIP);

        //then
        assertThat(result).isEqualTo(FINISHED);
    }

    @Test
    public void whenShootingOnBoardWithSingleShipProperStatesShouldBeReported() {
        //given
        Board board = new Board(SINGLE_SHIP_DEFINITION);

        //when
        Result result = board.shoot(FIELD_WITHOUT_SHIP);

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
        Board board = new Board(FOUR_SHIP_DEFINITIONS);

        //when
        Result result = board.shoot(FIELD_WITHOUT_SHIP);

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

