package controller;

import domain.lottoMachine.PurchaseResult;
import domain.money.Money;
import service.LottoService;
import view.InputVIew;
import view.OutputView;

import java.util.Optional;

public class LottoController {

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    private final LottoService lottoService;

    public void startGame() {
        PurchaseResult purchaseResult = getPurchasableMoney();
        OutputView.printLottos(purchaseResult);
        OutputView.printResult(lottoService.getResultStatistics(purchaseResult.lottos(), getLatestWinningNumbers()));
    }

    private PurchaseResult getPurchasableMoney() {
        Optional<PurchaseResult> purchaseResult;
        do {
            purchaseResult = inputPurchasableMoney();
        } while(purchaseResult.isEmpty());
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
            return Optional.of(new Money(InputVIew.getMoney()));
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return Optional.empty();
        }
    }

    private String getLatestWinningNumbers() {
        String lastWinnings;
        do {
            lastWinnings = InputVIew.getLastWeekWinnings();
        } while (lastWinnings.equals(InputVIew.INVALID_WINNINGS_INPUT));
        return lastWinnings;
    }
}
