package eu.chrost;

import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShooterTest {
    private static final Shooter.Point FIELD_WITHOUT_SHIP = Shooter.point(1, 2);
    private static final List<Triple<Shooter.Point, Integer, Boolean>> BOARD_WITHOUT_SHIPS = List.of();
    private static final int FINISHED = 3;

    @Test
    public void shouldReportFinishedWhenShootingOnBoardWithoutShips() {
        //given
        Shooter shooter = new Shooter(BOARD_WITHOUT_SHIPS);

        //when
        int result = shooter.shoot(FIELD_WITHOUT_SHIP);

        //then
        assertThat(result).isEqualTo(FINISHED);
    }
}
