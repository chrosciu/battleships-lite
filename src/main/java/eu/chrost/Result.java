package eu.chrost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum Result {
    MISSED(0),
    HIT(1),
    SUNK(2),
    FINISHED(3);

    private final int rank;

    public static Result fromRank(int rank) {
        return Arrays.stream(values())
                .filter(result -> result.rank == rank)
                .findFirst()
                .orElseThrow();
    }
}
