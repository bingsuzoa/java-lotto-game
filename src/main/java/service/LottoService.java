package service;

import domain.lotto.BonusBall;
import domain.lotto.Lotto;
import domain.lotto.lottoPricePolicy.LottoPricePolicy;
import domain.lottoMachine.LottoMachine;
import domain.lottoMachine.PurchaseResult;
import domain.lottoNumberGenerator.LottoNumberGenerator;
import domain.lottos.LottoStatistics;
import domain.lottos.Lottos;
import domain.lottos.Rank;
import domain.money.Money;

import java.util.Map;

public class LottoService {

    public LottoService(
            LottoNumberGenerator lottoNumberGenerator,
            LottoPricePolicy lottoPricePolicy
    ) {
        this.lottoMachine = new LottoMachine(lottoNumberGenerator);
        this.lottoPricePolicy = lottoPricePolicy;
    }

    public static final String INSUFFICIENT_LOTTO_PRICE = "로또를 구매할 수 있는 최소 금액이 모자랍니다.";

    private final LottoMachine lottoMachine;
    private final LottoPricePolicy lottoPricePolicy;

    public void validateSufficientMoney(Money money) {
        int lottoPrice = lottoPricePolicy.getLottoPrice();
        if (money.getMoney() < lottoPrice) {
            throw new IllegalArgumentException(INSUFFICIENT_LOTTO_PRICE);
        }
    }

    public PurchaseResult purchaseLottos(Money money) {
        return lottoMachine.buyLottos(money, lottoPricePolicy.getLottoPrice());
    }

    public LottoStatistics getResultStatistics(Lottos lottos, Lotto winningLotto, BonusBall bonusBall) {
        Map<Rank, Integer> matchedRankCounts = lottos.summarizeResults(winningLotto, bonusBall);
        double ratio = getRatioOfReturn(matchedRankCounts, lottos.getLottoCount());
        return new LottoStatistics(matchedRankCounts, ratio);
    }

    private double getRatioOfReturn(Map<Rank, Integer> matchedRankCounts, int lottoCount) {
        double totalPurchaseAmount = lottoCount * lottoPricePolicy.getLottoPrice();
        double winnings = 0;
        for (Rank rank : matchedRankCounts.keySet()) {
            int prize = rank.getPrize();
            int matchCount = matchedRankCounts.get(rank);
            winnings += (prize * matchCount);
        }
        double value = winnings / totalPurchaseAmount;
        return Math.round(value * 100.0) / 100.0;
    }

    public int getLottoPrice() {
        return lottoPricePolicy.getLottoPrice();
    }

}
