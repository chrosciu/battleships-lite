import com.chrosciu.Shooter;
import com.chrosciu.Shooter.Point;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShooterTest {
    private static final Point FIELD_WITHOUT_SHIP = Shooter.point(1, 2);
    private static final List<Triple<Point, Integer, Boolean>> NO_SHIPS = Arrays.asList();
    private static final int FINISHED = 3;

    @Test
    public void shouldReportFinishedOnShotIfNoShipsOnBoard() {
        //given
        Shooter shooter = new Shooter(NO_SHIPS);

        //when
        int result = shooter.shoot(FIELD_WITHOUT_SHIP);

        //then
        assertEquals(FINISHED, result);
    }


}
