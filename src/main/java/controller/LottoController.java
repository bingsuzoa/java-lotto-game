package controller;

import domain.lotto.BonusBall;
import domain.lotto.Lotto;
import domain.lottoMachine.PurchaseResult;
import domain.money.Money;
import service.LottoService;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LottoController {

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    private final LottoService lottoService;

    public void getInputAndStartGame() {
        PurchaseResult purchaseResult = purchaseLottos();
        OutputView.printLottos(purchaseResult);
        Lotto winningLotto = getValidManualLotto();
        BonusBall bonusBall = getValidBonusBall(winningLotto);
        OutputView.printResult(lottoService.getResultStatistics(purchaseResult.lottos(), winningLotto, bonusBall));
    }

    private PurchaseResult purchaseLottos() {
        Optional<PurchaseResult> purchaseResult;
        do {
            purchaseResult = inputMoneyAndCount();
        } while (purchaseResult.isEmpty());
        return purchaseResult.get();
    }

    private Optional<PurchaseResult> inputMoneyAndCount() {
        try {
            Money money = getPositiveMoney();
            lottoService.validateSufficientMoney(money);
            int manualLottoCount = getManualLottoCount(money);
            lottoService.checkManualBuy(money, manualLottoCount);
            return Optional.of(getManualLottos(money, manualLottoCount));
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return Optional.empty();
        }
    }

    private PurchaseResult getManualLottos(Money money, int manualLottoCount) {
        if (manualLottoCount == 0) {
            return lottoService.purchaseAutoLottosOnly(money);
        }
        List<Lotto> manualLottos = new ArrayList<>();
        OutputView.printMessage(OutputView.INPUTVIEW_MANUAL_LOTTO_NUMBERS);
        while (manualLottos.size() < manualLottoCount) {
            addManualLotto(InputView.getManualLottoNumbers(), manualLottos);
        }
        return lottoService.purchaseMixedLottos(manualLottos, money);
    }

    private void addManualLotto(String line, List<Lotto> manualLottos) {
        String[] tokens = line.split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String token : tokens) {
            numbers.add(Integer.parseInt(token.trim()));
        }
        manualLottos.add(new Lotto(numbers));
    }

    private int getManualLottoCount(Money money) {
        int manualLottoCount;
        do {
            manualLottoCount = inputManualLottoCount(money);
        } while (manualLottoCount == -1);
        return manualLottoCount;
    }

    private int inputManualLottoCount(Money money) {
        int invalidLottoCount = -1;
        int manualLottoCount;
        try {
            manualLottoCount = InputView.getNumberInput(OutputView.INPUTVIEW_MANUAL_LOTTO_COUNT);
            lottoService.checkManualBuy(money, manualLottoCount);
            return manualLottoCount;
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return invalidLottoCount;
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

    private Lotto getValidManualLotto() {
        OutputView.printMessage(OutputView.LAST_WEEK_WINNING_NUMBERS_MESSAGE);
        Optional<Lotto> manualLotto;
        do {
            manualLotto = inputValidManualNumbers();
        } while (manualLotto.isEmpty());
        return manualLotto.get();
    }

    private Optional<Lotto> inputValidManualNumbers() {
        try {
            return Optional.of(Lotto.from(InputView.getManualLottoNumbers()));
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
