package eu.chrost;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static eu.chrost.Orientation.HORIZONTAL;
import static eu.chrost.Orientation.VERTICAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class FieldTest {
    private static final int SOME_VERTICAL_COORDINATE = 4;
    private static final int SOME_HORIZONTAL_COORDINATE = 5;

    private static final int SOME_SHIFT = 3;

    private Field field;

    @BeforeEach
    public void setup() {
        field = new Field(SOME_VERTICAL_COORDINATE, SOME_HORIZONTAL_COORDINATE);
    }

    @Test
    public void shouldReturnProperVerticalCoordinate() {
        //when
        int x = field.x();

        //then
        assertEquals(SOME_VERTICAL_COORDINATE, x);
    }

    @Test
    public void shouldReturnProperHorizontalCoordinate() {
        //when
        int y = field.y();

        //then
        assertEquals(SOME_HORIZONTAL_COORDINATE, y);
    }

    @Test
    public void twoFieldsWithTheSameCoordinatesShouldBeEqual() {
        //given
        Field fieldWithTheSameCoordinates = new Field(SOME_VERTICAL_COORDINATE, SOME_HORIZONTAL_COORDINATE);

        //then
        assertEquals(field, fieldWithTheSameCoordinates);
    }

    @Test
    public void twoFieldsWithTheDifferentCoordinatesShouldBeEqual() {
        //given
        Field fieldWithDifferentCoordinates = new Field(SOME_VERTICAL_COORDINATE - 1, SOME_HORIZONTAL_COORDINATE + 1);

        //then
        assertNotEquals(field, fieldWithDifferentCoordinates);
    }

    @Test
    public void shouldProperlyShiftFieldVertically() {
        //when
        Field fieldShiftedVertically = field.shiftInOrientation(SOME_SHIFT, VERTICAL);

        //then
        assertEquals(fieldShiftedVertically, new Field(SOME_VERTICAL_COORDINATE, SOME_HORIZONTAL_COORDINATE + SOME_SHIFT));
    }

    @Test
    public void shouldProperlyShiftFieldHorizontally() {
        //when
        Field fieldShiftedHorizontally = field.shiftInOrientation(SOME_SHIFT, HORIZONTAL);

        //then
        assertEquals(fieldShiftedHorizontally, new Field(SOME_VERTICAL_COORDINATE + SOME_SHIFT, SOME_HORIZONTAL_COORDINATE));
    }
}

