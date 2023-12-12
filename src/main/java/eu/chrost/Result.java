package eu.chrost;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
enum Result {
    MISSED(0),
    HIT(1),
    SUNK(2),
    FINISHED(3);

    private final int rank;

    public static Result fromRank(int rank) {
        return Arrays.stream(values()).filter(result -> rank == result.rank).findFirst().get();
    }
}
