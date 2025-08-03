package domain.lottoNumberGenerator;

import domain.lotto.Lotto;
import domain.lotto.LottoRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberGenerator implements LottoNumberGenerator {

    @Override
    public List<Integer> generate() {
        List<Integer> numbers = new ArrayList<>();

        for (int i = LottoRule.MIN_NUMBER.value(); i <= LottoRule.MAX_NUMBER.value(); i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        List<Integer> randomNumbers = numbers.subList(0, 6);
        Collections.sort(randomNumbers);
        return randomNumbers;
    }
}
