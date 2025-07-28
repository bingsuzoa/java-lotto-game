package domain.lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    public Lotto(List<Integer> randomNumbers) {
        isValidateRandomNumbers(randomNumbers);
        this.lottoNumbers = randomNumbers;
    }

    public static final int LOTTO_PRICE = 1000;
    public static final int LOTTO_NUMBERS_COUNT = 6;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final String INVALID_LOTTO_NUMBERS = "로또 숫자 범위가 유효하지 않습니다.";
    public static final String INVALID_LOTTOS_SIZE = "로또 숫자 갯수가 유효하지 않습니다.";
    private final List<Integer> lottoNumbers;

    private void isValidateRandomNumbers(List<Integer> randomNumbers) {
        Set<Integer> uniqueSet = new HashSet<>(randomNumbers);
        for(int lottoNumber : randomNumbers) {
            if(lottoNumber < MIN_LOTTO_NUMBER || lottoNumber > MAX_LOTTO_NUMBER) {
                throw new IllegalArgumentException(INVALID_LOTTO_NUMBERS);
            }
        }
        if(uniqueSet.size() != LOTTO_NUMBERS_COUNT) {
            throw new IllegalArgumentException(INVALID_LOTTOS_SIZE);
        }
    }
    
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
        for (int i = 0; i < LOTTO_NUMBERS_COUNT; i++) {
            compareSet.add(lottoNumbers.get(i));
            compareSet.add(winningNumbers.get(i));
        }
        return totalNumbersCount - compareSet.size();
    }
}
