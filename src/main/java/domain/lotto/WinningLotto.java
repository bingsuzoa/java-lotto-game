package domain.lotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLotto {

    public WinningLotto(List<Integer> randomNumbers) {
        this.winningNumbers = List.copyOf(randomNumbers);
    }

    private final List<Integer> winningNumbers;

    public static WinningLotto ofLastWeekWinningNumbers(String lastWinnings) {
        List<Integer> randomNumbers = parseLastWeekWinningNumbers(lastWinnings);
        WinningLotto winningLotto = new WinningLotto(randomNumbers);
        return winningLotto;
    }

    private static List<Integer> parseLastWeekWinningNumbers(String lastWinnings) {
        return Arrays.stream(lastWinnings.replace(" ", "").split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public List<Integer> getLastWeekWinningLottos() {
        return winningNumbers;
    }
}
