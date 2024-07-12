package eu.chrost;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static eu.chrost.Orientation.HORIZONTAL;
import static eu.chrost.Orientation.VERTICAL;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class FieldTest {
    private static final int SOME_HORIZONTAL_COORDINATE = 4;
    private static final int SOME_VERTICAL_COORDINATE = 5;
    private static final int SOME_SHIFT = 3;

    private final Field field = new Field(SOME_HORIZONTAL_COORDINATE, SOME_VERTICAL_COORDINATE);

    @Test
    void should_return_proper_vertical_coordinate() {
        //when
        var x = field.x();

        //then
        assertThat(x).isEqualTo(SOME_HORIZONTAL_COORDINATE);
    }

    @Test
    void should_return_proper_horizontal_coordinate() {
        //when
        var y = field.y();

        //then
        assertThat(y).isEqualTo(SOME_VERTICAL_COORDINATE);
    }

    @Test
    void two_fields_with_the_same_coordinates_should_be_equal() {
        //given
        var fieldWithTheSameCoordinates = new Field(SOME_HORIZONTAL_COORDINATE, SOME_VERTICAL_COORDINATE);

        //then
        assertThat(fieldWithTheSameCoordinates).isEqualTo(field);
    }

    @Test
    void two_fields_with_different_coordinates_should__not_be_equal() {
        //given
        var fieldWithDifferentCoordinates = new Field(SOME_HORIZONTAL_COORDINATE - 1, SOME_VERTICAL_COORDINATE + 1);

        //then
        assertThat(fieldWithDifferentCoordinates).isNotEqualTo(field);
    }

    @Test
    void should_properly_shift_field_vertically() {
        //when
        var fieldShiftedVertically = field.shift(SOME_SHIFT, VERTICAL);

        //then
        assertThat(fieldShiftedVertically).isEqualTo(new Field(SOME_HORIZONTAL_COORDINATE, SOME_VERTICAL_COORDINATE + SOME_SHIFT));
    }

    @Test
    void should_properly_shift_field_horizontally() {
        //when
        var fieldShiftedHorizontally = field.shift(SOME_SHIFT, HORIZONTAL);

        //then
        assertThat(fieldShiftedHorizontally).isEqualTo(new Field(SOME_HORIZONTAL_COORDINATE + SOME_SHIFT, SOME_VERTICAL_COORDINATE));
    }


}
