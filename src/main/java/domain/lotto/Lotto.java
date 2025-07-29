package domain.lotto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    public Lotto(List<Integer> randomNumbers) {
        validateRandomNumbers(randomNumbers);
        this.lottoNumbers = List.copyOf(randomNumbers);
    }

    public static final int LOTTO_NUMBERS_COUNT = 6;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final String INVALID_LOTTO_NUMBERS = "로또 숫자 범위가 유효하지 않습니다.";
    public static final String INVALID_LOTTOS_SIZE = "로또 숫자 갯수가 유효하지 않습니다.";
    private final List<Integer> lottoNumbers;

    public static Lotto from(String randomNumbers) {
        List<Integer> numbers = Arrays.stream(randomNumbers.replace(" ", "").split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return new Lotto(numbers);
    }

    public void validateRandomNumbers(List<Integer> randomNumbers) {
        validateNumberRange(randomNumbers);
        validateUniqueNumbers(randomNumbers);
    }

    private void validateNumberRange(List<Integer> randomNumbers) {
        for (int lottoNumber : randomNumbers) {
            if (lottoNumber < MIN_LOTTO_NUMBER || lottoNumber > MAX_LOTTO_NUMBER) {
                throw new IllegalArgumentException(INVALID_LOTTO_NUMBERS);
            }
        }
    }

    private void validateUniqueNumbers(List<Integer> randomNumbers) {
        Set<Integer> uniqueSet = new HashSet<>(randomNumbers);
        if (uniqueSet.size() != LOTTO_NUMBERS_COUNT || randomNumbers.size() != LOTTO_NUMBERS_COUNT) {
            throw new IllegalArgumentException(INVALID_LOTTOS_SIZE);
        }
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }

    public String toFormattedString() {
        return "[ㅡ사" + lottoNumbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")) + "]";
    }

    public boolean isMatchBonusBall(BonusBall bonusNumber) {
        return lottoNumbers.contains(bonusNumber.getBonusNumber());
    }

    public int getMatchCount(Lotto winningLotto) {
        return countMatchNumbers(winningLotto);
    }

    private int countMatchNumbers(Lotto winningLotto) {
        int totalNumbersCount = LOTTO_NUMBERS_COUNT * 2;
        List<Integer> winningNumbers = winningLotto.getLottoNumbers();

        Set<Integer> compareSet = new HashSet<>();
        for (int i = 0; i < LOTTO_NUMBERS_COUNT; i++) {
            compareSet.add(lottoNumbers.get(i));
            compareSet.add(winningNumbers.get(i));
        }
        return totalNumbersCount - compareSet.size();
    }
}
