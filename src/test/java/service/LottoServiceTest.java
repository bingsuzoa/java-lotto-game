package service;

import domain.lotto.lottoPricePolicy.FixedLottoPricePolicy;
import domain.lotto.lottoPricePolicy.LottoPricePolicy;
import domain.lottoMachine.LottoMachine;
import domain.lottoMachine.PurchaseResult;
import domain.lottoNumberGenerator.LottoNumberGenerator;
import domain.lottoNumberGenerator.RandomNumberGenerator;
import domain.money.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoServiceTest {

    private LottoNumberGenerator lottoNumberGenerator = new RandomNumberGenerator();
    private LottoPricePolicy lottoPricePolicy = new FixedLottoPricePolicy();
    private LottoService lottoService = new LottoService(lottoNumberGenerator, lottoPricePolicy);

    @Test
    @DisplayName("돈을 주면 로또와 거스름돈을 주는지 확인")
    void 로또_구매() {
        PurchaseResult purchaseResult = lottoService.purchaseLottos(new Money(14500));
        Assertions.assertEquals(purchaseResult.getLottoCount(), 14);
        Assertions.assertEquals(purchaseResult.change(), 500);
    }


    /// /////예외 테스트
    @Test
    @DisplayName("로또 구매할 수 있는 최소 금액이 안되면 예외 발생")
    void 로또_구매_최소_금액_미달시_예외() {
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            lottoService.validateSufficientMoney(new Money(900));
        });
        Assertions.assertEquals(e.getMessage(), LottoMachine.INSUFFICIENT_LOTTO_PRICE);
    }
}
