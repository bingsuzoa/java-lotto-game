package domain.lotto;

public class BonusBall {

    public BonusBall(int bonusBall) {
        if (bonusBall < LottoRule.MIN_NUMBER.value() || bonusBall > LottoRule.MAX_NUMBER.value()) {
            throw new IllegalArgumentException(INVALID_BONUS_BALL_RANGE);
        }
        this.bonusBall = bonusBall;
    }

    public static final String INVALID_BONUS_BALL_RANGE = "입력하신 보너스 번호는 유효하지 않는 숫자 범위입니다.";
    private final int bonusBall;

    public int getBonusBall() {
        return bonusBall;
    }
}
