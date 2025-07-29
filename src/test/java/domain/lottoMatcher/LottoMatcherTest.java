package domain.lottoMatcher;

import domain.lotto.Lotto;
import domain.lottos.Lottos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class LottoMatcherTest {

    private LottoMatcher lottoMatcher = new LottoMatcher();

    @Test
    @DisplayName("로또 비교하는 테스트")
    void 로또_비교() {
        List<Integer> numbers1 = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto1 = new Lotto(numbers1);

        List<Integer> numbers2 = List.of(6, 7, 1, 13, 22, 21);
        Lotto lotto2 = new Lotto(numbers2);

        List<Lotto> issuedLottos = List.of(lotto1, lotto2);
        Lottos lottos = new Lottos(issuedLottos);

        String winningNumbers = "1,2,3,6,23,24";
        Lotto winningLotto = Lotto.from(winningNumbers);

        Map<Rank, Integer> matchedRankCounts = lottoMatcher.summarizeResults(lottos, winningLotto);
        Assertions.assertEquals(matchedRankCounts.get(Rank.FIRST), 0);
        Assertions.assertEquals(matchedRankCounts.get(Rank.SECOND), 0);
        Assertions.assertEquals(matchedRankCounts.get(Rank.THIRD), 1);
        Assertions.assertEquals(matchedRankCounts.get(Rank.FOURTH), 0);
    }
}
