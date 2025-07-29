package domain.lotto;

public class BonusBall {

    public BonusBall(int bonusNumber, Lotto winningLotto) {
        validateBonusBall(bonusNumber, winningLotto);
        this.bonusNumber = bonusNumber;
    }

    public static final String INVALID_BONUS_BALL = "당첨 로또에 이미 존재하는 숫자입니다.";
    private final int bonusNumber;

    private void validateBonusBall(int bonusNumber, Lotto winningLotto) {
        if (bonusNumber < Lotto.MIN_LOTTO_NUMBER || bonusNumber > Lotto.MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(Lotto.INVALID_LOTTO_NUMBERS);
        }

        if (winningLotto.getLottoNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(INVALID_BONUS_BALL);
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
