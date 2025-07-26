package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    public Lotto(int round, List<Integer> randomNumbers) {
        this.round = round;
        this.lottoNumbers = randomNumbers;
    }

    private final int round;
    private final List<Integer> lottoNumbers;


    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }

    public int getMatchCount(WinningLotto winningLotto) {
        return countMatchNumbers(winningLotto);
    }

    private int countMatchNumbers(WinningLotto winningLotto) {
        int totalNumbersCount = 12;
        List<Integer> winningNumbers = winningLotto.getWinningNumbers();

        Set<Integer> compareSet = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            compareSet.add(lottoNumbers.get(i));
            compareSet.add(winningNumbers.get(i));
        }
        return totalNumbersCount - compareSet.size();
    }
}
