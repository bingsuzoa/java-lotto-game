package domain.lottoMachine;

import domain.lottoNumberGenerator.LottoNumberGenerator;
import domain.lottoNumberGenerator.RandomNumberGenerator;
import domain.money.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class LottoMachineTest {

    @Test
    @DisplayName("손님으로부터 돈을 받고 금액에 해당하는 로또 제공하는지 확인")
    void 손님으로부터_돈_받고_로또_제공() {
        LottoNumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoMachine lottoMachine = new LottoMachine(numberGenerator);
        PurchaseResult purchaseResult = lottoMachine.buyLottos(new Money(13000), 1000);
        Assertions.assertEquals(purchaseResult.getLottoCount(), 13);
    }

    @Test
    @DisplayName("손님으로부터 돈을 받고 거스름돈 제공 확인")
    void 손님으로부터_돈_받고_거스름돈_제공() {
        LottoNumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoMachine lottoMachine = new LottoMachine(numberGenerator);
        PurchaseResult purchaseResult = lottoMachine.buyLottos(new Money(13500), 1000);
        Assertions.assertEquals(purchaseResult.change(), 500);
    }
}
