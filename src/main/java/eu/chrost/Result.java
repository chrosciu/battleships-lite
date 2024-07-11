package eu.chrost;

public enum Result {
    MISSED(0),
    HIT(1),
    SUNK(2),
    FINISHED(3);

    private final int rank;

    Result(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public static Result fromRank(int rank) {
        for (Result result: values()) {
            if (result.getRank() == rank) {
                return result;
            }
        }
        throw new IllegalArgumentException("Invalid rank: " + rank);
    }
}
