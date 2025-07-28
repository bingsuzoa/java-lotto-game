package domain.money;

import domain.lotto.Lotto;

public class Money {

    public Money(int money) {
        if(money < Lotto.LottoPrice) {
            throw new IllegalArgumentException(INSUFFICIENT_PAYMENT_MESSAGE);
        }
        this.money = money;
    }

    public static final String INSUFFICIENT_PAYMENT_MESSAGE = "로또를 구매할 수 있는 최소 금액이 모자랍니다.";
    private final int money;

    public int getMoney() {
        return money;
    }

    public int getChange(int lottoCount) {
        return money - lottoCount * Lotto.LottoPrice;
    }
}
