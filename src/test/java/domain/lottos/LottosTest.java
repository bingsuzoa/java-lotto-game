package domain.lottos;

import domain.lotto.Lotto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LottosTest {


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
