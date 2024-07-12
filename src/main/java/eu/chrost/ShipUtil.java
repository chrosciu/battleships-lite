package eu.chrost;

import java.util.List;

public class ShipUtil {
    public static boolean areGivenShipsSunk(List<Ship> ships) {
        boolean isFinished = true;
        for (int i = 0; i < ships.size() && isFinished; ++i) {
            for (int j = 0; j < ships.get(i).size() && isFinished; ++j) {
                isFinished &= ships.get(i).get(j).isHit();
            }
        }
        return isFinished;
    }
}
