package eu.chrost;

import org.junit.jupiter.api.Test;

import static eu.chrost.Orientation.HORIZONTAL;
import static eu.chrost.Orientation.VERTICAL;
import static org.assertj.core.api.Assertions.assertThat;

public class FieldTest {
    @Test
    void shiftFieldHorizontally() {
        //given
        var field = new Field(1, 2);

        //when
        var shiftedField = field.shiftBy(3, HORIZONTAL);

        //then
        assertThat(shiftedField.getX()).isEqualTo(4);
        assertThat(shiftedField.getY()).isEqualTo(2);
    }

    @Test
    void shiftFieldVertically() {
        //given
        var field = new Field(1, 2);

        //when
        var shiftedField = field.shiftBy(3, VERTICAL);

        //then
        assertThat(shiftedField.getX()).isEqualTo(1);
        assertThat(shiftedField.getY()).isEqualTo(5);
    }

    @Test
    void twoFieldsWithSameCoordinatesShouldBeEqual() {
        //given
        var fieldOne = new Field(1, 2);
        var fieldTwo = new Field(1, 2);

        //when / then
        assertThat(fieldOne).isEqualTo(fieldTwo);
    }

    @Test
    void twoFieldsWithDifferentCoordinatesShouldNotBeEqual() {
        //given
        var fieldOne = new Field(1, 2);
        var fieldTwo = new Field(2, 2);

        //when / then
        assertThat(fieldOne).isNotEqualTo(fieldTwo);
    }


}
