package lotto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class WinningLottoTest {

    @Test
    @DisplayName("정답 번호 외부에서 수정 불가한지 확인")
    void 수정_불가_확인() {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6);
        int round = 1;
        WinningLotto winningLotto = new WinningLotto(round, lottoNumbers);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            winningLotto.getWinningNumbers().add(5, 7);
        });
    }
}
