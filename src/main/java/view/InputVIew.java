package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputVIew {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INVALID_INPUT = "당첨 번호는 [,]로만 구분 가능하며 6개의 숫자만 입력해주세요.";
    private static final String INVALID_LOTTO_NUMBER = "당첨 번호는 1~45의 숫자만 가능합니다.";

    public static final String INVALID_WINNINGS_INPUT = "invalid winnings input";

    public static int getMoney() {
        int invalidMoneyInput = -1;
        try {
            OutputView.printGetMoney();
            int money = scanner.nextInt();
            scanner.nextLine();
            return money;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            OutputView.printMessage(OutputView.INPUTVIEW_INVALID_GET_MONEY_TYPE);
            return invalidMoneyInput;
        }
    }

    public static String getLastWeekWinnings() {
        try {
            OutputView.printMessage(OutputView.LAST_WEEK_WINNING_NUMBERS_MESSAGE);
            String lastWeekWinnings = scanner.nextLine();
            isValidLottoNumbers(lastWeekWinnings);
            return lastWeekWinnings;
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return INVALID_WINNINGS_INPUT;
        }
    }

    private static void isValidLottoNumbers(String input) {
        input = input.replace(" ", "");
        if (!input.matches("^\\d+(,\\d+){5}$")) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }

        for (String value : input.split(",")) {
            int lottoNumber = Integer.parseInt(value);
            if (lottoNumber < 1 || lottoNumber > 45) {
                throw new IllegalArgumentException(INVALID_LOTTO_NUMBER);
            }
        }
    }
}
