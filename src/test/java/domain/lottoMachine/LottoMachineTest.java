package domain.lottoMachine;

import domain.lotto.lottoPricePolicy.FixedLottoPricePolicy;
import domain.lotto.lottoPricePolicy.LottoPricePolicy;
import domain.lottoNumberGenerator.LottoNumberGenerator;
import domain.lottoNumberGenerator.RandomNumberGenerator;
import domain.money.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.lottoMachine.LottoMachine.INSUFFICIENT_LOTTO_PRICE;

public class LottoMachineTest {

    @Test
    @DisplayName("손님으로부터 돈을 받고 금액에 해당하는 로또 제공하는지 확인")
    void 손님으로부터_돈_받고_로또_제공() {
        LottoPricePolicy lottoPricePolicy = new FixedLottoPricePolicy();
        LottoNumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoMachine lottoMachine = new LottoMachine(numberGenerator, lottoPricePolicy);
        PurchaseResult purchaseResult = lottoMachine.buyLottos(new Money(13000));
        Assertions.assertEquals(purchaseResult.getLottoCount(), 13);
    }

    @Test
    @DisplayName("손님으로부터 돈을 받고 거스름돈 제공 확인")
    void 손님으로부터_돈_받고_거스름돈_제공() {
        LottoPricePolicy lottoPricePolicy = new FixedLottoPricePolicy();
        LottoNumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoMachine lottoMachine = new LottoMachine(numberGenerator, lottoPricePolicy);
        PurchaseResult purchaseResult = lottoMachine.buyLottos(new Money(13500));
        Assertions.assertEquals(purchaseResult.change(), 500);
    }

    /// /////예외 테스트
    @Test
    @DisplayName("로또를 구매할 수 있는 최소 금액에 해당하지 않으면 예외 발생")
    void 로또_구매할_수_있는_최소_금액_아니면_예외() {
        LottoPricePolicy lottoPricePolicy = new FixedLottoPricePolicy();
        LottoNumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoMachine lottoMachine = new LottoMachine(numberGenerator, lottoPricePolicy);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            lottoMachine.validateSufficientMoney(new Money(lottoMachine.getLottoPrice() - 1));
        });
        Assertions.assertEquals(e.getMessage(), INSUFFICIENT_LOTTO_PRICE);
    }
}
