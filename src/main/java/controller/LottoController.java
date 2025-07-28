package controller;

import domain.lottoMachine.PurchaseResult;
import domain.money.Money;
import service.LottoService;
import view.InputVIew;
import view.OutputView;

public class LottoController {

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    private final LottoService lottoService;
    private static final Money INSUFFICENT_MONEY = null;

    public void startGame() {
        PurchaseResult purchaseResult = lottoService.purchaseLottos(getPurchasbleMoney());
        OutputView.printLottos(purchaseResult);
        OutputView.printResult(lottoService.getResultStatistics(purchaseResult.lottos(), getLatestWinningNumbers()));
    }

    private Money getPurchasbleMoney() {
        Money money;
        do {
            money = inputPurchasableMoney();
        } while (money == INSUFFICENT_MONEY);
        return money;
    }

    private Money inputPurchasableMoney() {
        try {
            return new Money(InputVIew.getMoney());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return INSUFFICENT_MONEY;
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
