package eu.chrost;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ShipFieldTest {

    private static final int SOME_VERTICAL_COORDINATE = 4;
    private static final int SOME_HORIZONTAL_COORDINATE = 5;
    private static final Field TEST_SHIP_FIELD = new Field(SOME_VERTICAL_COORDINATE, SOME_HORIZONTAL_COORDINATE);

    private final ShipField shipField = new ShipField(TEST_SHIP_FIELD);

    @Test
    public void should_return_proper_field_value() {
        //when
        Field field = shipField.getField();

        //then
        assertThat(field).isEqualTo(TEST_SHIP_FIELD);
    }

    @Test
    public void should_return_not_hit_after_init() {
        //when
        boolean hit = shipField.isHit();

        //then
        assertThat(hit).isFalse();
    }

    @Test
    public void should_return_hit_after_marked_as_hit() {
        //when
        shipField.markAsHit();
        boolean hit = shipField.isHit();

        //then
        assertThat(hit).isTrue();
    }
}
