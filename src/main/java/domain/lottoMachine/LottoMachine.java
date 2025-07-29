package domain.lottoMachine;

import domain.lotto.Lotto;
import domain.lotto.lottoPricePolicy.LottoPricePolicy;
import domain.lottoNumberGenerator.LottoNumberGenerator;
import domain.lottos.Lottos;
import domain.money.Money;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    public LottoMachine(
            LottoNumberGenerator numberGenerator,
            LottoPricePolicy lottoPricePolicy
    ) {
        this.numberGenerator = numberGenerator;
        this.lottoPricePolicy = lottoPricePolicy;
    }

    public static final String INSUFFICIENT_LOTTO_PRICE = "로또를 구매할 수 있는 최소 금액이 모자랍니다.";
    private final LottoNumberGenerator numberGenerator;
    private final LottoPricePolicy lottoPricePolicy;

    public void validateSufficientMoney(Money money) {
        int lottoPrice = lottoPricePolicy.getLottoPrice();
        if(money.getMoney() < lottoPrice) {
            throw new IllegalArgumentException(INSUFFICIENT_LOTTO_PRICE);
        }
    }

    public PurchaseResult buyLottos(Money money) {
        int lottoPrice = lottoPricePolicy.getLottoPrice();
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

    public int getLottoPrice() {
        return lottoPricePolicy.getLottoPrice();
    }
}
