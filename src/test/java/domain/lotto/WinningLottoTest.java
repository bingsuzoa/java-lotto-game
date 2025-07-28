package domain.lotto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class WinningLottoTest {

    @Test
    @DisplayName("정답 번호 외부에서 수정 불가한지 확인")
    void 수정_불가_확인() {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6);
        WinningLotto winningLotto = new WinningLotto(lottoNumbers);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            winningLotto.getLastWeekWinningLottos().add(5, 7);
        });
    }

    /// //예외 테스트
    @Test
    @DisplayName("랜덤 숫자가 1 ~ 45 범위가 아닌 경우 예외 발생")
    void 랜덤_숫자_범위_확인() {
        List<Integer> lottoNumbers = List.of(0, 1, 2, 3, 4, 5);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                new WinningLotto(lottoNumbers));
        Assertions.assertEquals(e.getMessage(), Lotto.INVALID_LOTTO_NUMBERS);
    }

    @Test
    @DisplayName("랜덤 숫자 갯수가 허용된 갯수가 아닌 경우 예외 발생")
    void 랜덤_숫자가_허용된_갯수인지_확인() {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6, 7);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                new WinningLotto(lottoNumbers));
        Assertions.assertEquals(e.getMessage(), Lotto.INVALID_LOTTOS_SIZE);
    }
}
