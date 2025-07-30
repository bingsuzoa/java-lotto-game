package domain.lottos;

import domain.lotto.BonusBall;
import domain.lotto.Lotto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class LottosTest {

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

        BonusBall bonusBall = new BonusBall(7);
        Map<Rank, Integer> matchedRankCounts = lottos.summarizeResults(winningLotto, bonusBall);
        Assertions.assertEquals(matchedRankCounts.get(Rank.FIRST), 0);
        Assertions.assertEquals(matchedRankCounts.get(Rank.SECOND), 0);
        Assertions.assertEquals(matchedRankCounts.get(Rank.SECOND_BONUS), 0);
        Assertions.assertEquals(matchedRankCounts.get(Rank.THIRD), 1);
        Assertions.assertEquals(matchedRankCounts.get(Rank.FOURTH), 0);
    }

    @Test
    @DisplayName("로또 1등 당첨 확인 테스트")
    void 로또_1등_당첨_비교() {
        List<Integer> numbers1 = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto1 = new Lotto(numbers1);
        Lottos lottos = new Lottos(List.of(lotto1));

        String winningNumbers = "1,2,3,4,5,6";
        Lotto winningLotto = Lotto.from(winningNumbers);

        BonusBall bonusBall = new BonusBall(7);
        Map<Rank, Integer> matchedRankCounts = lottos.summarizeResults(winningLotto, bonusBall);
        Assertions.assertEquals(matchedRankCounts.get(Rank.FIRST), 1);
        Assertions.assertEquals(matchedRankCounts.get(Rank.SECOND), 0);
        Assertions.assertEquals(matchedRankCounts.get(Rank.SECOND_BONUS), 0);
        Assertions.assertEquals(matchedRankCounts.get(Rank.THIRD), 0);
        Assertions.assertEquals(matchedRankCounts.get(Rank.FOURTH), 0);
    }

    @Test
    @DisplayName("로또 2등 + 보너스 번호 미당첨 확인 테스트")
    void 로또2등_보너스번호_미당첨() {
        List<Integer> numbers1 = List.of(1, 2, 3, 4, 5, 8);
        Lotto lotto1 = new Lotto(numbers1);
        Lottos lottos = new Lottos(List.of(lotto1));

        String winningNumbers = "1,2,3,4,5,6";
        Lotto winningLotto = Lotto.from(winningNumbers);

        BonusBall bonusBall = new BonusBall(7);
        Map<Rank, Integer> matchedRankCounts = lottos.summarizeResults(winningLotto, bonusBall);
        Assertions.assertEquals(matchedRankCounts.get(Rank.FIRST), 0);
        Assertions.assertEquals(matchedRankCounts.get(Rank.SECOND), 1);
        Assertions.assertEquals(matchedRankCounts.get(Rank.SECOND_BONUS), 0);
        Assertions.assertEquals(matchedRankCounts.get(Rank.THIRD), 0);
        Assertions.assertEquals(matchedRankCounts.get(Rank.FOURTH), 0);
    }

    @Test
    @DisplayName("로또 2등 + 보너스 번호 당첨 확인 테스트")
    void 로또2등_보너스번호_당첨() {
        List<Integer> numbers1 = List.of(1, 2, 3, 4, 5, 7);
        Lotto lotto1 = new Lotto(numbers1);
        Lottos lottos = new Lottos(List.of(lotto1));

        String winningNumbers = "1,2,3,4,5,6";
        Lotto winningLotto = Lotto.from(winningNumbers);

        BonusBall bonusBall = new BonusBall(7);
        Map<Rank, Integer> matchedRankCounts = lottos.summarizeResults(winningLotto, bonusBall);
        Assertions.assertEquals(matchedRankCounts.get(Rank.FIRST), 0);
        Assertions.assertEquals(matchedRankCounts.get(Rank.SECOND), 0);
        Assertions.assertEquals(matchedRankCounts.get(Rank.SECOND_BONUS), 1);
        Assertions.assertEquals(matchedRankCounts.get(Rank.THIRD), 0);
        Assertions.assertEquals(matchedRankCounts.get(Rank.FOURTH), 0);
    }


    @Test
    @DisplayName("수익률 확인")
    void 수익률_확인_테스트() {
        List<Integer> numbers1 = List.of(8, 21, 23, 41, 42, 43);
        Lotto lotto1 = new Lotto(numbers1);

        List<Integer> numbers2 = List.of(3, 5, 11, 16, 32, 38);
        Lotto lotto2 = new Lotto(numbers2);

        List<Integer> numbers3 = List.of(7, 11, 16, 35, 36, 44);
        Lotto lotto3 = new Lotto(numbers3);

        List<Integer> numbers4 = List.of(1, 8, 11, 31, 41, 42);
        Lotto lotto4 = new Lotto(numbers4);

        List<Integer> numbers5 = List.of(1, 2, 3, 31, 41, 42);
        Lotto lotto5 = new Lotto(numbers5);

        List<Lotto> issuedLottos = List.of(lotto1, lotto2, lotto3, lotto4, lotto5);
        Lottos lottos = new Lottos(issuedLottos);

        Assertions.assertEquals(lottos.getLottoCount(), 5);
    }
}
