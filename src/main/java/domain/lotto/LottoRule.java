package domain.lotto;

public enum LottoRule {
    MIN_NUMBER(1),
    MAX_NUMBER(45),
    LOTTO_COUNT(6);

    private final int number;

    LottoRule(int number) {
        this.number = number;
    }

    public int value() {
        return number;
    }
}
