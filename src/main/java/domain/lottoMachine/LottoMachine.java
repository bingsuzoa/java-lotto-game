package domain.lottoMachine;

import domain.lotto.Lotto;
import domain.lottoNumberGenerator.LottoNumberGenerator;
import domain.lottos.Lottos;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    public LottoMachine(LottoNumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public static final String INSUFFICIENT_PAYMENT_MESSAGE = "로또를 구매할 수 있는 최소 금액이 모자랍니다.";
    private final LottoNumberGenerator numberGenerator;

    public PurchaseResult buyLottos(int money) {
        if (money < Lotto.LottoPrice) {
            throw new IllegalArgumentException(INSUFFICIENT_PAYMENT_MESSAGE);
        }
        int lottoCount = money / Lotto.LottoPrice;
        Lottos lottos = issue(lottoCount);
        return new PurchaseResult(lottos, money - (lottoCount * Lotto.LottoPrice));
    }

    private Lottos issue(int ticketCount) {
        List<Lotto> issuedLottos = new ArrayList<>();

        for (int i = 0; i < ticketCount; i++) {
            issuedLottos.add(new Lotto(numberGenerator.generate()));
        }

        return new Lottos(issuedLottos);
    }
}
