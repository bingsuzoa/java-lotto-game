package domain.lotto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BonusBallTest {

    @Test
    @DisplayName("보너스 볼 생성확인")
    void 보너스_볼_생성_확인() {
        Lotto winningLotto = Lotto.from("2,3,4,5,6,7");
        BonusBall bonusBall = new BonusBall(1, winningLotto);
        Assertions.assertEquals(bonusBall.getBonusNumber(), 1);
    }

    /// ////예외 테스트
    @ParameterizedTest
    @DisplayName("보너스 볼 생성 시 1 ~ 45 범위 바깥에 있는 숫자를 넘길 경우 예외 발생")
    @ValueSource(ints = {0, -1, 46})
    void 보너스_볼_생성_확인(int value) {
        Lotto winningLotto = Lotto.from("2,3,4,5,6,7");
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new BonusBall(value, winningLotto);
        });
        Assertions.assertEquals(e.getMessage(), Lotto.INVALID_LOTTO_NUMBERS);
    }

    @Test
    @DisplayName("보너스 볼 생성 시 당첨 로또에 있는 숫자 입력했을 경우 예외 발생")
    void 당첨_로또에_있는_숫자_입력_시_예외() {
        Lotto winningLotto = Lotto.from("2,3,4,5,6,7");
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new BonusBall(2, winningLotto);
        });
        Assertions.assertEquals(e.getMessage(), BonusBall.INVALID_BONUS_BALL);
    }
}
