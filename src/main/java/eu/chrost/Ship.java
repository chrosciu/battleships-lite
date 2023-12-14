package eu.chrost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Ship {
    private final Shooter.Point firstField;
    private final int length;
    private final boolean vertical;
}
