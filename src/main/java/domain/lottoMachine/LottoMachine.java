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

    public PurchaseResult buyLottos(Money money, int lottoPrice) {
        int lottoCount = money.getMoney() / lottoPrice;
        Lottos lottos = issue(lottoCount);
        return new PurchaseResult(lottos, money.getChange(lottoCount, lottoPrice));
    }

    private Lottos issue(int ticketCount) {
        List<Lotto> issuedLottos = new ArrayList<>();

        for (int i = 0; i < ticketCount; i++) {
            issuedLottos.add(new Lotto(numberGenerator.generate()));
        }

        return new Lottos(issuedLottos);
    }
}
