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

    public static final String INVALID_LOTTO_NUMBERS = "로또 숫자 범위가 유효하지 않습니다.";
    public static final String INVALID_LOTTOS_SIZE = "로또 숫자 갯수가 유효하지 않습니다.";
    public static final String INVALID_BONUS_BALL = "당첨 로또에 이미 존재하는 숫자입니다.";
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
            if (lottoNumber < LottoRule.MIN_NUMBER.value() || lottoNumber > LottoRule.MAX_NUMBER.value()) {
                throw new IllegalArgumentException(INVALID_LOTTO_NUMBERS);
            }
        }
    }

    private void validateUniqueNumbers(List<Integer> randomNumbers) {
        Set<Integer> uniqueSet = new HashSet<>(randomNumbers);
        if (uniqueSet.size() != LottoRule.LOTTO_COUNT.value() || randomNumbers.size() != LottoRule.LOTTO_COUNT.value()) {
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

    public void validateBonusNumber(int bonusNumber) {
        if(lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(INVALID_BONUS_BALL);
        }
    }

    public boolean isContainNumber(int number) {
        return lottoNumbers.contains(number);
    }

    public int getMatchCount(Lotto winningLotto) {
        return countMatchNumbers(winningLotto);
    }

    private int countMatchNumbers(Lotto winningLotto) {
        int totalNumbersCount = LottoRule.LOTTO_COUNT.value() * 2;
        List<Integer> winningNumbers = winningLotto.getLottoNumbers();

        Set<Integer> compareSet = new HashSet<>();
        for (int i = 0; i < LottoRule.LOTTO_COUNT.value(); i++) {
            compareSet.add(lottoNumbers.get(i));
            compareSet.add(winningNumbers.get(i));
        }
        return totalNumbersCount - compareSet.size();
    }
}
