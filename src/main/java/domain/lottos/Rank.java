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
        if (matchCount == 3 && !isBonusHit) return FOURTH;
        if (matchCount == 4 && !isBonusHit) return THIRD;
        if (matchCount == 5 && !isBonusHit) return SECOND;
        if (matchCount == 5 && isBonusHit) return SECOND_BONUS;
        if (matchCount == 6 && !isBonusHit) return FIRST;
        return MISS;
    }
}
