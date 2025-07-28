package domain.lottoMachine;

import domain.lottoNumberGenerator.LottoNumberGenerator;
import domain.lottoNumberGenerator.RandomNumberGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    @Test
    @DisplayName("손님으로부터 돈을 받고 금액에 해당하는 로또 제공하는지 확인")
    void 손님으로부터_돈_받고_로또_제공() {
        LottoNumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoMachine lottoMachine = new LottoMachine(numberGenerator);
        int money = 13000;
        PurchaseResult purchaseResult = lottoMachine.buyLottos(money);
        Assertions.assertEquals(purchaseResult.getLottoCount(), 13);
    }

    /// /////예외 테스트
    @Test
    @DisplayName("손님이 지불한 금액이 원하는 로또 갯수보다 적으면 예외 발생")
    void 지불한_금액_확인() {
        LottoNumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoMachine lottoMachine = new LottoMachine(numberGenerator);
        int money = 900;
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            lottoMachine.buyLottos(money);
        });
        Assertions.assertEquals(e.getMessage(), LottoMachine.INSUFFICIENT_PAYMENT_MESSAGE);
    }
}
