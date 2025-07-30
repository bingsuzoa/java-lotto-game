package controller;

import domain.lotto.BonusBall;
import domain.lotto.Lotto;
import domain.lottoMachine.PurchaseResult;
import domain.money.Money;
import service.LottoService;
import view.InputView;
import view.OutputView;

import java.util.Optional;

public class LottoController {

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    private final LottoService lottoService;

    public void getInputAndStartGame() {
        PurchaseResult purchaseResult = getPurchasableMoney();
        OutputView.printLottos(purchaseResult);
        Lotto winningLotto = getValidWinningLotto();
        BonusBall bonusBall = getValidBonusBall(winningLotto);
        OutputView.printResult(lottoService.getResultStatistics(purchaseResult.lottos(), winningLotto, bonusBall));
    }

    private PurchaseResult getPurchasableMoney() {
        Optional<PurchaseResult> purchaseResult;
        do {
            purchaseResult = inputPurchasableMoney();
        } while (purchaseResult.isEmpty());
        return purchaseResult.get();
    }

    private Optional<PurchaseResult> inputPurchasableMoney() {
        try {
            Money money = getPositiveMoney();
            lottoService.validateSufficientMoney(money);
            return Optional.of(lottoService.purchaseLottos(money));
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return Optional.empty();
        }
    }

    private Money getPositiveMoney() {
        Optional<Money> money;
        do {
            money = inputPositiveMoney();
        } while (money.isEmpty());
        return money.get();
    }

    private Optional<Money> inputPositiveMoney() {
        try {
            return Optional.of(new Money(InputView.getNumberInput(OutputView.INPUTVIEW_GET_MONEY_MESSAGE)));
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return Optional.empty();
        }
    }

    private Lotto getValidWinningLotto() {
        Optional<Lotto> lastWinnings;
        do {
            lastWinnings = inputValidWinningNumbers();
        } while (lastWinnings.isEmpty());
        return lastWinnings.get();
    }

    private Optional<Lotto> inputValidWinningNumbers() {
        try {
            return Optional.of(Lotto.from(InputView.getLastWeekWinnings()));
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return Optional.empty();
        }
    }

    private BonusBall getValidBonusBall(Lotto winningLotto) {
        Optional<BonusBall> bonusBall;
        do {
            bonusBall = inputBonusBall(winningLotto);
        } while (bonusBall.isEmpty());
        return bonusBall.get();
    }

    private Optional<BonusBall> inputBonusBall(Lotto winningLoto) {
        try {
            int bonusNumber = InputView.getNumberInput(OutputView.INPUTVIEW_GET_BONUS_MESSAGE);
            winningLoto.validateBonusNumber(bonusNumber);
            return Optional.of(new BonusBall(bonusNumber));
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return Optional.empty();
        }
    }
}
