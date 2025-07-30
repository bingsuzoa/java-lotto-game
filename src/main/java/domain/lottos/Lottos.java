package domain.lottos;

import domain.lotto.BonusBall;
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

    public Map<Rank, Integer> summarizeResults(Lotto winningLotto, BonusBall bonusBall) {
        Map<Rank, Integer> matchedRankCounts = new HashMap<>();
        int initCount = 0;
        for (Rank rank : Rank.values()) {
            matchedRankCounts.put(rank, initCount);
        }
        return countMatchedLottosBy(winningLotto, bonusBall, matchedRankCounts);
    }

    private Map<Rank, Integer> countMatchedLottosBy(Lotto winningLotto, BonusBall bonusBall, Map<Rank, Integer> matchedRankCounts) {
        for (Lotto lotto : lottos) {
            MatchedResult matchedResult = getMatchedResult(lotto, winningLotto, bonusBall);
            Rank rank = Rank.valueOf(matchedResult.matched(), matchedResult.isBonusHit());
            matchedRankCounts.computeIfPresent(rank, (k, v) -> v + 1);
        }
        matchedRankCounts.remove(Rank.MISS);
        return matchedRankCounts;
    }

    private MatchedResult getMatchedResult(Lotto lotto, Lotto winningLotto, BonusBall bonusBall) {
        int matched = lotto.getMatchCount(winningLotto);
        return new MatchedResult(matched, isBonusHit(lotto, matched, bonusBall));
    }

    private boolean isBonusHit(Lotto lotto, int matched, BonusBall bonusBall) {
        if (matched == Rank.SECOND_BONUS.getMatchCount()) {
            return lotto.isContainNumber(bonusBall.getBonusBall());
        }
        return false;
    }
}
