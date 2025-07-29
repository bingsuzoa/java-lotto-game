package service;

import domain.lotto.WinningLotto;
import domain.lotto.lottoPricePolicy.LottoPricePolicy;
import domain.lottoMachine.LottoMachine;
import domain.lottoMachine.PurchaseResult;
import domain.lottoNumberGenerator.LottoNumberGenerator;
import domain.lottos.LottoStatistics;
import domain.lottos.Lottos;
import domain.money.Money;

public class LottoService {

    public LottoService(
            LottoNumberGenerator lottoNumberGenerator,
            LottoPricePolicy lottoPricePolicy
    ) {
        this.lottoMachine = new LottoMachine(lottoNumberGenerator, lottoPricePolicy);
    }

    private final LottoMachine lottoMachine;

    public PurchaseResult purchaseLottos(Money money) {
        return lottoMachine.buyLottos(money);
    }

    public void validateSufficientMoney(Money money) {
        lottoMachine.validateSufficientMoney(money);
    }

    public LottoStatistics getResultStatistics(Lottos lottos, String winnings) {
        WinningLotto winningLotto = new WinningLotto(winnings);
        return lottos.summarizeResults(winningLotto, lottoMachine.getLottoPrice());
    }
}
