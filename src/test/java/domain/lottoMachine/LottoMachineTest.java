package domain.lottoMachine;

import domain.lotto.Lotto;
import domain.lottoNumberGenerator.LottoNumberGenerator;
import domain.lottoNumberGenerator.RandomNumberGenerator;
import domain.money.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class LottoMachineTest {

    @Test
    @DisplayName("손님으로부터 돈을 받고 금액에 해당하는 로또 제공하는지 확인")
    void 손님으로부터_돈_받고_로또_제공() {
        LottoNumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoMachine lottoMachine = new LottoMachine(numberGenerator);
        PurchaseResult purchaseResult = lottoMachine.buyAutoLottosOnly(new Money(13000), 1000);
        Assertions.assertEquals(purchaseResult.getLottoCount(), 13);
    }

    @Test
    @DisplayName("손님으로부터 돈을 받고 거스름돈 제공 확인")
    void 손님으로부터_돈_받고_거스름돈_제공() {
        LottoNumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoMachine lottoMachine = new LottoMachine(numberGenerator);
        PurchaseResult purchaseResult = lottoMachine.buyAutoLottosOnly(new Money(13500), 1000);
        Assertions.assertEquals(purchaseResult.change(), 500);
    }

    @Test
    @DisplayName("수동으로 구매할 로또 + 자동 구매할 로또 제공")
    void 수동_구매_자동_구매() {
        LottoNumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoMachine lottoMachine = new LottoMachine(numberGenerator);
        List<Lotto> manualLottos = new ArrayList<>();
        manualLottos.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        manualLottos.add(new Lotto(List.of(7, 8, 9, 10, 11, 12)));
        manualLottos.add(new Lotto(List.of(14, 15, 16, 17, 18, 19)));
        PurchaseResult purchaseResult = lottoMachine.buyMixedLottos(new Money(14000), manualLottos, 1000);
        Assertions.assertEquals(purchaseResult.getLottoCount(), 14);
    }
}
