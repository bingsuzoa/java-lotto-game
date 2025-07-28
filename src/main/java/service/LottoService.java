package service;

import domain.lotto.WinningLotto;
import domain.lottoMachine.LottoMachine;
import domain.lottoMachine.PurchaseResult;
import domain.lottoNumberGenerator.LottoNumberGenerator;
import domain.lottos.LottoStatistics;
import domain.lottos.Lottos;
import domain.money.Money;

public class LottoService {

    public LottoService(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoMachine = new LottoMachine(lottoNumberGenerator);
    }

    private final LottoMachine lottoMachine;

    public PurchaseResult purchaseLottos(Money money) {
        return lottoMachine.buyLottos(money);
    }

    public LottoStatistics getResultStatistics(Lottos lottos, String winnings) {
        WinningLotto winningLotto = WinningLotto.ofLastWeekWinningNumbers(winnings);
        return lottos.summarizeResults(winningLotto);
    }
}
