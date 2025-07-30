package domain.lottos;

public enum Rank {

    FIRST(6, 2_000_000_000),
    SECOND_BONUS(5, 30_000_000),
    SECOND(5, 1_500_000),
    THIRD(4, 50000),
    FOURTH(3, 5000),
    MISS(0, 0);

    private final int matchCount;
    private final int prize;

    Rank(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }

    public static Rank valueOf(int matchCount, boolean isBonusHit) {
        switch (matchCount) {
            case 6:
                return FIRST;
            case 5:
                return isBonusHit ? SECOND_BONUS : SECOND;
            case 4:
                return THIRD;
            case 3:
                return FOURTH;
            default:
                return MISS;
        }
    }
}
