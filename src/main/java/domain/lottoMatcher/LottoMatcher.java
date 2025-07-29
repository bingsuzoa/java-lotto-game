package domain.lottoMatcher;

import domain.lotto.Lotto;
import domain.lottos.Lottos;

import java.util.HashMap;
import java.util.Map;

public class LottoMatcher {

    public Map<Rank, Integer> summarizeResults(Lottos lottos, Lotto winningLotto) {
        Map<Rank, Integer> matchedRankCounts = new HashMap<>();
        for (int matchCount = 3; matchCount <= 6; matchCount++) {
            Rank rank = Rank.valueOf(matchCount);
            matchedRankCounts.put(rank, countMatchedLottosBy(matchCount, lottos, winningLotto));
        }
        return matchedRankCounts;
    }

    private int countMatchedLottosBy(int expectedMatchCount, Lottos lottos, Lotto winningLotto) {
        int count = 0;
        for (Lotto lotto : lottos.getLottos()) {
            count += toBinaryMatchScore(expectedMatchCount, lotto.getMatchCount(winningLotto));
        }
        return count;
    }

    private int toBinaryMatchScore(int expected, int actual) {
        return expected == actual ? 1 : 0;
    }
}
