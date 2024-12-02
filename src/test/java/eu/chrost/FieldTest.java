package eu.chrost;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static eu.chrost.Orientation.HORIZONTAL;
import static eu.chrost.Orientation.VERTICAL;
import static org.assertj.core.api.Assertions.assertThat;

class FieldTest {
    private static final int SOME_VERTICAL_COORDINATE = 4;
    private static final int SOME_HORIZONTAL_COORDINATE = 5;

    private static final int SOME_SHIFT_LENGTH = 3;

    private Field field;

    @BeforeEach
    public void setup() {
        field = new Field(SOME_VERTICAL_COORDINATE, SOME_HORIZONTAL_COORDINATE);
    }

    @Test
    public void shouldReturnProperVerticalCoordinate() {
        //when
        int x = field.getX();

        //then
        assertThat(x).isEqualTo(SOME_VERTICAL_COORDINATE);
    }

    @Test
    public void shouldReturnProperHorizontalCoordinate() {
        //when
        int y = field.getY();

        //then
        assertThat(y).isEqualTo(SOME_HORIZONTAL_COORDINATE);
    }

    @Test
    public void twoFieldsWithTheSameCoordinatesShouldBeEqual() {
        //given
        Field fieldWithTheSameCoordinates = new Field(SOME_VERTICAL_COORDINATE, SOME_HORIZONTAL_COORDINATE);

        //then
        assertThat(fieldWithTheSameCoordinates).isEqualTo(field);
    }

    @Test
    public void twoFieldsWithTheDifferentCoordinatesShouldBeEqual() {
        //given
        Field fieldWithDifferentCoordinates = new Field(SOME_VERTICAL_COORDINATE - 1, SOME_HORIZONTAL_COORDINATE + 1);

        //then
        assertThat(fieldWithDifferentCoordinates).isNotEqualTo(field);
    }

    @Test
    public void shouldProperlyShiftFieldVertically() {
        //when
        Field fieldShiftedVertically = field.shift(SOME_SHIFT_LENGTH, VERTICAL);

        //then
        assertThat(fieldShiftedVertically)
                .isEqualTo(new Field(SOME_VERTICAL_COORDINATE, SOME_HORIZONTAL_COORDINATE + SOME_SHIFT_LENGTH));
    }

    @Test
    public void shouldProperlyShiftFieldHorizontally() {
        //when
        Field fieldShiftedHorizontally = field.shift(SOME_SHIFT_LENGTH, HORIZONTAL);

        //then
        assertThat(fieldShiftedHorizontally)
                .isEqualTo(new Field(SOME_VERTICAL_COORDINATE + SOME_SHIFT_LENGTH, SOME_HORIZONTAL_COORDINATE));
    }
}

