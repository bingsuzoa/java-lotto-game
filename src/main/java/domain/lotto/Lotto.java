package domain.lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    public Lotto(List<Integer> randomNumbers) {
        this.lottoNumbers = List.copyOf(randomNumbers);
    }

    public static final int LottoPrice = 1000;
    private final List<Integer> lottoNumbers;


    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }

    public String toFormattedString() {
        return "[" + lottoNumbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")) + "]";
    }

    public int getMatchCount(WinningLotto winningLotto) {
        return countMatchNumbers(winningLotto);
    }

    private int countMatchNumbers(WinningLotto winningLotto) {
        int totalNumbersCount = 12;
        List<Integer> winningNumbers = winningLotto.getLastWeekWinningLottos();

        Set<Integer> compareSet = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            compareSet.add(lottoNumbers.get(i));
            compareSet.add(winningNumbers.get(i));
        }
        return totalNumbersCount - compareSet.size();
    }
}
