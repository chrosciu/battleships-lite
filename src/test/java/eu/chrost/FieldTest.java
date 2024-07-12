package eu.chrost;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class FieldTest {
    private static final int SOME_VERTICAL_COORDINATE = 4;
    private static final int SOME_HORIZONTAL_COORDINATE = 5;

    private final Field field = new Field(SOME_VERTICAL_COORDINATE, SOME_HORIZONTAL_COORDINATE);

    @Test
    public void should_return_proper_vertical_coordinate() {
        //when
        int x = field.x();

        //then
        assertThat(x).isEqualTo(SOME_VERTICAL_COORDINATE);
    }

    @Test
    public void should_return_proper_horizontal_coordinate() {
        //when
        int y = field.y();

        //then
        assertThat(y).isEqualTo(SOME_HORIZONTAL_COORDINATE);
    }

    @Test
    public void two_fields_with_the_same_coordinates_should_be_equal() {
        //given
        Field fieldWithTheSameCoordinates = new Field(SOME_VERTICAL_COORDINATE, SOME_HORIZONTAL_COORDINATE);

        //then
        assertThat(fieldWithTheSameCoordinates).isEqualTo(field);
    }

    @Test
    public void two_fields_with_different_coordinates_should__not_be_equal() {
        //given
        Field fieldWithDifferentCoordinates = new Field(SOME_VERTICAL_COORDINATE - 1, SOME_HORIZONTAL_COORDINATE + 1);

        //then
        assertThat(fieldWithDifferentCoordinates).isNotEqualTo(field);
    }


}
