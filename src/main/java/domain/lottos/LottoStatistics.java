package domain.lottoMatcher;

import java.util.Map;

public record LottoStatistics(
        Map<Rank, Integer> matchedRankCounts,
        double ratio
) {
}
