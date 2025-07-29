package domain.lotto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static domain.lotto.Lotto.*;

public class WinningLotto {

    public WinningLotto(String lastWinnings) {
        List<Integer> lastWeekWinningNumbers = parseLastWeekWinningNumbers(lastWinnings);
        validateRandomNumbers(lastWeekWinningNumbers);
        winningLottos = List.copyOf(lastWeekWinningNumbers);
    }

    private final static int WINNING_LOTTO_NUMBERS_COUNT = 6;
    private final List<Integer> winningLottos;

    private void validateRandomNumbers(List<Integer> randomNumbers) {
        validateUniqueNumbers(randomNumbers);
        validateNumberRange(randomNumbers);
    }

    private void validateUniqueNumbers(List<Integer> randomNumbers) {
        Set<Integer> uniqueSet = new HashSet<>(randomNumbers);
        if (uniqueSet.size() != WINNING_LOTTO_NUMBERS_COUNT) {
            throw new IllegalArgumentException(INVALID_LOTTOS_SIZE);
        }
    }

    private void validateNumberRange(List<Integer> randomNumbers) {
        for (int lottoNumber : randomNumbers) {
            if (lottoNumber < MIN_LOTTO_NUMBER || lottoNumber > MAX_LOTTO_NUMBER) {
                throw new IllegalArgumentException(INVALID_LOTTO_NUMBERS);
            }
        }
    }

    private List<Integer> parseLastWeekWinningNumbers(String lastWinnings) {
        return Arrays.stream(lastWinnings.replace(" ", "").split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public List<Integer> getLastWeekWinningLottos() {
        return winningLottos;
    }
}
