package view;

import domain.lottoMachine.PurchaseResult;
import domain.lottos.LottoStatistics;
import domain.lottos.Rank;

import java.util.Map;

public class OutputView {

    public static final String INPUTVIEW_GET_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String INPUTVIEW_GET_BONUS_MESSAGE = "보너스 볼을 입력해 주세요.";
    public static final String INPUTVIEW_INVALID_GET_MONEY_TYPE = "구입금액은 숫자만 입력가능합니다.";
    public static final String PURCHASE_RESULT_MESSAGE = "개를 구매했습니다.";
    public static final String LAST_WEEK_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String INPUTVIEW_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    public static final String INPUTVIEW_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";


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

        for (Rank rank : matchedRankCounts.keySet()) {
            System.out.println(generateMessageByRank(rank, matchedRankCounts));
        }
        System.out.println("총 수익률은 " + ratio + "입니다.");
    }

    private static String generateMessageByRank(Rank rank, Map<Rank, Integer> matchedRankCounts) {
        int matchCount = rank.getMatchCount();
        int prize = rank.getPrize();
        int matched = matchedRankCounts.get(rank);

        String firstSentence = matchCount + "개 일치";
        String prizeSentence = "(" + prize + "원)";
        String lastSentence = matched + "개";

        if (rank.equals(Rank.SECOND_BONUS)) {
            return firstSentence + ", 보너스 볼 일치" + prizeSentence + " - " + lastSentence;
        }
        return firstSentence + " " + prizeSentence + "- " + lastSentence;
    }
}
