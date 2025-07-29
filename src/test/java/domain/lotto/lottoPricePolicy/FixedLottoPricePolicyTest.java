package domain.lotto.lottoPricePolicy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FixedLottoPricePolicyTest {

    @Test
    @DisplayName("가격 반환 테스트")
    void 가격_반환_테스트() {
        LottoPricePolicy lottoPricePolicy = new FixedLottoPricePolicy();
        Assertions.assertEquals(lottoPricePolicy.getLottoPrice(), 1000);
    }
}
