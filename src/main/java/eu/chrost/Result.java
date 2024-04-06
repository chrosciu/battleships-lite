package eu.chrost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Result {
    MISSED,
    HIT,
    SUNK,
    FINISHED;
}
