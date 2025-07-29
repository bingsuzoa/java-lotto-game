package domain.lottos;

import domain.lotto.Lotto;

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
        for (Lotto lotto : lottos) {
            sb.append(lotto.toFormattedString() + "\n");
        }
        return sb.toString();
    }

    public int getLottoCount() {
        return lottos.size();
    }

    public Map<Rank, Integer> summarizeResults(Lotto winningLotto) {
        Map<Rank, Integer> matchedRankCounts = new HashMap<>();
        for (int matchCount = 3; matchCount <= 6; matchCount++) {
            Rank rank = Rank.valueOf(matchCount);
            matchedRankCounts.put(rank, countMatchedLottosBy(matchCount, winningLotto));
        }
        return matchedRankCounts;
    }

    private int countMatchedLottosBy(int expectedMatchCount, Lotto winningLotto) {
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
