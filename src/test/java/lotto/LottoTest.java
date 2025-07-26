package lotto;

import lottoNumberGenerator.LottoNumberGenerator;
import lottoNumberGenerator.RandomNumberGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LottoTest {

    LottoNumberGenerator numberGenerator = new RandomNumberGenerator();

    @Test
    @DisplayName("로또 객체 생성 시 6개의 랜덤 숫자 리스트를 갖는다.")
    void 로또_생성_시_6개_랜덤_숫자_리스트_갖기() {
        Lotto lotto = new Lotto(1, numberGenerator.generate());
        List<Integer> lottoNumbers = lotto.getLottoNumbers();
        Assertions.assertEquals(lottoNumbers.size(), 6);
    }

    @Test
    @DisplayName("이번주 당첨 번호와 비교해서 몇 개와 동일한지 비교")
    void 당첨_번호와_비교하기() {
        Lotto lotto = new Lotto(1, numberGenerator.generate());
        WinningLotto winningLotto = new WinningLotto(1, numberGenerator.generate());
        int hittingCount = lotto.getMatchCount(winningLotto);
        Assertions.assertTrue(hittingCount >= 0 && hittingCount <= 6);
    }
}
