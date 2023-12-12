package eu.chrost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class Field {
    private final int x;
    private final int y;
}
