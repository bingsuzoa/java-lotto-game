package lotto;

import java.util.List;

public class WinningLotto {

    public WinningLotto(int round, List<Integer> randomNumbers) {
        this.round = round;
        this.winningNumbers = List.copyOf(randomNumbers);
    }

    private final int round;
    private final List<Integer> winningNumbers;

    public int getRound() {
        return round;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }
}
