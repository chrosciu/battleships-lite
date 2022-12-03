package com.chrosciu;

import org.junit.Before;
import org.junit.Test;

import static com.chrosciu.Direction.HORIZONTAL;
import static com.chrosciu.Direction.VERTICAL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FieldTest {
    private static final int SOME_VERTICAL_COORDINATE = 4;
    private static final int SOME_HORIZONTAL_COORDINATE = 5;

    private static final int SOME_SHIFT = 3;

    private Field field;

    @Before
    public void setup() {
        field = Field.of(SOME_VERTICAL_COORDINATE, SOME_HORIZONTAL_COORDINATE);
    }

    @Test
    public void shouldReturnProperVerticalCoordinate() {
        //when
        int x = field.getX();

        //then
        assertEquals(SOME_VERTICAL_COORDINATE, x);
    }

    @Test
    public void shouldReturnProperHorizontalCoordinate() {
        //when
        int y = field.getY();

        //then
        assertEquals(SOME_HORIZONTAL_COORDINATE, y);
    }

    @Test
    public void twoFieldsWithTheSameCoordinatesShouldBeEqual() {
        //given
        Field fieldWithTheSameCoordinates = Field.of(SOME_VERTICAL_COORDINATE, SOME_HORIZONTAL_COORDINATE);

        //then
        assertEquals(field, fieldWithTheSameCoordinates);
    }

    @Test
    public void twoFieldsWithTheDifferentCoordinatesShouldBeEqual() {
        //given
        Field fieldWithDifferentCoordinates = Field.of(SOME_VERTICAL_COORDINATE - 1, SOME_HORIZONTAL_COORDINATE + 1);

        //then
        assertNotEquals(field, fieldWithDifferentCoordinates);
    }

    @Test
    public void shouldProperlyShiftFieldVertically() {
        //when
        Field fieldShiftedVertically = field.shiftInDirection(SOME_SHIFT, VERTICAL);

        //then
        assertEquals(fieldShiftedVertically, Field.of(SOME_VERTICAL_COORDINATE, SOME_HORIZONTAL_COORDINATE + SOME_SHIFT));
    }

    @Test
    public void shouldProperlyShiftFieldHorizontally() {
        //when
        Field fieldShiftedHorizontally = field.shiftInDirection(SOME_SHIFT, HORIZONTAL);

        //then
        assertEquals(fieldShiftedHorizontally, Field.of(SOME_VERTICAL_COORDINATE + SOME_SHIFT, SOME_HORIZONTAL_COORDINATE));
    }
}
