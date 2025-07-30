package domain.lottos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RankTest {


    @Test
    @DisplayName("랭크 1등 확인 테스트")
    void 랭크_1등_확인() {
        Rank rankFalse = Rank.valueOf(6, false);
        Rank rankTrue = Rank.valueOf(6, true);
        Assertions.assertEquals(rankFalse, Rank.FIRST);
        Assertions.assertEquals(rankTrue, Rank.FIRST);
    }

    @Test
    @DisplayName("랭크 2등 확인 테스트")
    void 랭크_2등_확인() {
        Rank rankFalse = Rank.valueOf(5, false);
        Rank rankTrue = Rank.valueOf(5, true);
        Assertions.assertEquals(rankFalse, Rank.SECOND);
        Assertions.assertEquals(rankTrue, Rank.SECOND_BONUS);
    }

    @Test
    @DisplayName("랭크 3등 확인 테스트")
    void 랭크_3등_확인() {
        Rank rankFalse = Rank.valueOf(4, false);
        Rank rankTrue = Rank.valueOf(4, true);
        Assertions.assertEquals(rankFalse, Rank.THIRD);
        Assertions.assertEquals(rankTrue, Rank.THIRD);
    }

    @Test
    @DisplayName("랭크 4등 확인 테스트")
    void 랭크_4등_확인() {
        Rank rankFalse = Rank.valueOf(3, false);
        Rank rankTrue = Rank.valueOf(3, true);
        Assertions.assertEquals(rankFalse, Rank.FOURTH);
        Assertions.assertEquals(rankTrue, Rank.FOURTH);
    }

    @ParameterizedTest
    @DisplayName("랭크 MISS 확인 테스트")
    @ValueSource(ints = {1, 2, 0, 7})
    void 랭크_MISS등_확인(int value) {
        Rank rankFalse = Rank.valueOf(value, false);
        Rank rankTrue = Rank.valueOf(value, true);
        Assertions.assertEquals(rankFalse, Rank.MISS);
        Assertions.assertEquals(rankTrue, Rank.MISS);
    }
}
