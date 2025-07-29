package domain.lotto;

import domain.lottoNumberGenerator.LottoNumberGenerator;
import domain.lottoNumberGenerator.RandomNumberGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LottoTest {

    LottoNumberGenerator numberGenerator = new RandomNumberGenerator();

    @Test
    @DisplayName("로또 객체 생성 시 6개의 랜덤 숫자 리스트를 갖는다.")
    void 로또_생성_시_6개_랜덤_숫자_리스트_갖기() {
        Lotto lotto = new Lotto(numberGenerator.generate());
        List<Integer> lottoNumbers = lotto.getLottoNumbers();
        Assertions.assertEquals(lottoNumbers.size(), 6);
    }

    @Test
    @DisplayName("이번주 당첨 번호와 비교해서 몇 개와 동일한지 비교")
    void 당첨_번호와_비교하기() {
        Lotto lotto = new Lotto(numberGenerator.generate());
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");
        int hittingCount = lotto.getMatchCount(winningLotto);
        Assertions.assertTrue(hittingCount >= 0 && hittingCount <= 6);
    }

    /// ///예외 테스트
    @Test
    @DisplayName("랜덤 숫자가 1 ~ 45 범위가 아닌 경우 예외 발생")
    void 랜덤_숫자_범위_확인() {
        List<Integer> randomNumbers = List.of(0, 1, 2, 3, 4, 5);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Lotto(randomNumbers));
        Assertions.assertEquals(e.getMessage(), Lotto.INVALID_LOTTO_NUMBERS);
    }

    @Test
    @DisplayName("랜덤 숫자 갯수가 허용된 갯수가 아닌 경우 예외 발생")
    void 랜덤_숫자가_허용된_갯수인지_확인() {
        List<Integer> randomNumbers = List.of(1, 2, 3, 4, 5, 6, 7);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Lotto(randomNumbers));
        Assertions.assertEquals(e.getMessage(), Lotto.INVALID_LOTTOS_SIZE);
    }
}
