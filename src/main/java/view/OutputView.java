package view;

import domain.lottoMachine.PurchaseResult;
import domain.lottos.LottoStatistics;
import domain.lottos.Rank;

import java.util.Map;

public class OutputView {

    public static final String INPUTVIEW_GET_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String INPUTVIEW_INVALID_GET_MONEY_TYPE = "구입금액은 숫자만 입력가능합니다.";
    public static final String PURCHASE_RESULT_MESSAGE = "개를 구매했습니다.";
    public static final String LAST_WEEK_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String REPURCHASE_LOTTO_MESSAGE = "로또 재구매를 희망하시면 1, 희망하지 않으면 0을 입력해주세요.";


    public static void printGetMoney() {
        System.out.println(INPUTVIEW_GET_MONEY_MESSAGE);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printLottos(PurchaseResult purchaseResult) {
        int lottoCount = purchaseResult.getLottoCount();
        System.out.println(lottoCount + PURCHASE_RESULT_MESSAGE);
        System.out.println(purchaseResult.getFormattedString());
    }

    public static void printResult(LottoStatistics lottoStatistics) {
        Map<Rank, Integer> matchedRankCounts = lottoStatistics.matchedRankCounts();
        double ratio = lottoStatistics.ratio();
        System.out.println("당첨 통계");
        System.out.println("-------------");

        for(Rank rank : matchedRankCounts.keySet()) {
            int matchCount = rank.getMatchCount();
            int prize = rank.getPrize();
            int actualCount = matchedRankCounts.get(rank);
            System.out.println(matchCount + "개 일치 (" + prize + "원)-" + actualCount + "개");
        }
        System.out.println("총 수익률은 " + ratio + "입니다.");
    }
}
