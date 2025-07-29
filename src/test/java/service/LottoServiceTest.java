package service;

import domain.lotto.Lotto;
import domain.lotto.lottoPricePolicy.FixedLottoPricePolicy;
import domain.lotto.lottoPricePolicy.LottoPricePolicy;
import domain.lottoMachine.LottoMachine;
import domain.lottoMachine.PurchaseResult;
import domain.lottoNumberGenerator.LottoNumberGenerator;
import domain.lottoNumberGenerator.RandomNumberGenerator;
import domain.lottos.Lottos;
import domain.money.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        String winningNumbers = "1,2,3,4,5,6";
        Assertions.assertEquals(lottoService.getResultStatistics(lottos, winningNumbers).ratio(), 1.0);
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
