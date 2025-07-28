package controller;

import domain.lottoMachine.PurchaseResult;
import service.LottoService;
import view.InputVIew;
import view.OutputView;

public class LottoController {

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    private final LottoService lottoService;

    public void startGame() {
        PurchaseResult purchaseResult;
        do {
            purchaseResult = purchaseLottos(getMoney());
        } while(purchaseResult == null);
        OutputView.printLottos(purchaseResult);
        OutputView.printResult(lottoService.getResultStatistics(purchaseResult.lottos(), getLatestWinningNumbers()));
    }

    private PurchaseResult purchaseLottos(int money) {
        PurchaseResult purchaseResult;
        try {
            purchaseResult = lottoService.purchaseLottos(money);
            return purchaseResult;
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return null;
        }
    }

    private int getMoney() {
        int money;
        do {
            money = InputVIew.getMoney();
        } while (money == -1);
        return money;
    }

    private String getLatestWinningNumbers() {
        String lastWinnings;
        do {
            lastWinnings = InputVIew.getLastWeekWinnings();
        } while (lastWinnings.equals(InputVIew.INVALID_WINNINGS_INPUT));
        return lastWinnings;
    }
}
