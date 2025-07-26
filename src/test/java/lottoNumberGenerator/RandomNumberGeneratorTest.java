package lottoNumberGenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RandomNumberGeneratorTest {

    LottoNumberGenerator numberGenerator = new RandomNumberGenerator();

    @Test
    @DisplayName("랜덤 숫자 6개 얻는 테스트")
    void 랜덤_숫자_얻기() {
        List<Integer> lottoNumbers = numberGenerator.generate();
        Assertions.assertEquals(lottoNumbers.size(), 6);
    }

    @Test
    @DisplayName("랜덤 숫자가 모두 서로 다른지 확인하는 테스트")
    void 랜덤_숫자는_모두_달라야한다() {
        List<Integer> lottoNumbers = numberGenerator.generate();
        boolean isNotSame = true;
        for (int i = 0; i < lottoNumbers.size() - 1; i++) {
            if (lottoNumbers.get(i) == lottoNumbers.get(i + 1)) {
                isNotSame = false;
                break;
            }
        }
        Assertions.assertTrue(isNotSame);
    }

    @Test
    @DisplayName("랜덤 숫자는 1 ~ 45까지의 정수인지 확인하는 테스트")
    void 랜덤_숫자_범위_확인() {
        List<Integer> lottoNumbers = numberGenerator.generate();
        boolean isMoreThanOneLessThenFortyFive = true;
        for (int i = 0; i < lottoNumbers.size(); i++) {
            if (lottoNumbers.get(i) < 1 || lottoNumbers.get(i) > 45) {
                isMoreThanOneLessThenFortyFive = false;
                break;
            }
        }
        Assertions.assertTrue(isMoreThanOneLessThenFortyFive);
    }
}
