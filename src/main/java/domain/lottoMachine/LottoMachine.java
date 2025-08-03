package domain.lottoMachine;

import domain.lotto.Lotto;
import domain.lottoNumberGenerator.LottoNumberGenerator;
import domain.lottos.Lottos;
import domain.money.Money;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    public LottoMachine(LottoNumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    private final LottoNumberGenerator numberGenerator;

    public PurchaseResult buyAutoLottosOnly(Money money, int lottoPrice) {
        int autoCount = money.getMoney() / lottoPrice;
        Lottos lottos = issue(autoCount);
        return new PurchaseResult(0, autoCount, lottos, money.getChange(autoCount, lottoPrice));
    }

    public PurchaseResult buyMixedLottos(Money money, List<Lotto> manualLottos, int lottoPrice) {
        int autoLottoCount = (money.getMoney() / lottoPrice) - manualLottos.size();

        List<Lotto> lottos = new ArrayList<>();

        for (Lotto lotto : manualLottos) {
            lottos.add(lotto);
        }
        for (int i = 0; i < autoLottoCount; i++) {
            lottos.add(new Lotto(numberGenerator.generate()));
        }
        return new PurchaseResult(manualLottos.size(), autoLottoCount, new Lottos(lottos), money.getChange(manualLottos.size() + autoLottoCount, lottoPrice));
    }

    private Lottos issue(int ticketCount) {
        List<Lotto> issuedLottos = new ArrayList<>();

        for (int i = 0; i < ticketCount; i++) {
            issuedLottos.add(new Lotto(numberGenerator.generate()));
        }

        return new Lottos(issuedLottos);
    }
}
