package eu.chrost;

import lombok.Getter;

@Getter
public class PointH {
    private final Field field;
    private boolean hit;

    public PointH(Field field) {
        this.field = field;
        this.hit = false;
    }

    public void markAsHit() {
        this.hit = true;
    }
}
