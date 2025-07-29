package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputVIew {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INVALID_INPUT = "입력 형태가 올바르지 않습니다. 숫자 사이에 [,]의 입력만 가능합니다. ";

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

    public static String getLastWeekWinnings() throws IllegalArgumentException {
        OutputView.printMessage(OutputView.LAST_WEEK_WINNING_NUMBERS_MESSAGE);
        String lastWeekWinnings = scanner.nextLine();
        isValidLottoNumbers(lastWeekWinnings);
        return lastWeekWinnings;
    }

    private static void isValidLottoNumbers(String input) {
        input = input.replace(" ", "");
        if (!input.matches("^\\d+(,\\d+)*$")) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
    }
}
