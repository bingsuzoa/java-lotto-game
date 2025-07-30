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
        BonusBall bonusBall = new BonusBall(1);
        Assertions.assertEquals(bonusBall.getBonusBall(), 1);
    }

    /// ////예외 테스트
    @ParameterizedTest
    @DisplayName("보너스 볼 생성 시 1 ~ 45 범위 바깥에 있는 숫자를 넘길 경우 예외 발생")
    @ValueSource(ints = {0, -1, 46})
    void 보너스_볼_생성_확인(int value) {
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new BonusBall(value);
        });
        Assertions.assertEquals(e.getMessage(), BonusBall.INVALID_BONUS_BALL_RANGE);
    }
}
