package domain.lottos;

import domain.lotto.Lotto;
import domain.lotto.WinningLotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lottos {

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    private final List<Lotto> lottos;

    public List<Lotto> getLottos() {
        return lottos;
    }

    public String toFormattedString() {
        StringBuilder sb = new StringBuilder();
        for(Lotto lotto : lottos) {
            sb.append(lotto.toFormattedString() + "\n");
        }
        return sb.toString();
    }

    public LottoStatistics summarizeResults(WinningLotto winningLotto) {
        Map<Rank, Integer> matchedRankCounts = new HashMap<>();
        for (int matchCount = 3; matchCount <= 6; matchCount++) {
            Rank rank = Rank.valueOf(matchCount);
            matchedRankCounts.put(rank, countMatchedLottosBy(matchCount, winningLotto));
        }
        double ratio = getRatioOfReturn(matchedRankCounts);
        return new LottoStatistics(matchedRankCounts, ratio);
    }

    private double getRatioOfReturn(Map<Rank, Integer> matchedRankCounts) {
        double totalPurchaseAmount = lottos.size() * Lotto.LottoPrice;
        double winnings = 0;
        for (Rank rank : matchedRankCounts.keySet()) {
            int prize = rank.getPrize();
            int matchCount = matchedRankCounts.get(rank);
            winnings += (prize * matchCount);
        }
        double value = winnings / totalPurchaseAmount;
        return Math.round(value * 100.0) / 100.0;
    }

    private int countMatchedLottosBy(int expectedMatchCount, WinningLotto winningLotto) {
        int count = 0;
        for (Lotto lotto : lottos) {
            count += toBinaryMatchScore(expectedMatchCount, lotto.getMatchCount(winningLotto));
        }
        return count;
    }

    private int toBinaryMatchScore(int expected, int actual) {
        return expected == actual ? 1 : 0;
    }
}
