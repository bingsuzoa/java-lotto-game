package domain.money;

public class Money {

    public Money(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException(INSUFFICIENT_MONEY_MESSAGE);
        }
        this.money = money;
    }

    public static final String INSUFFICIENT_MONEY_MESSAGE = "금액은 1원 이상 내주셔야합니다.";
    private final int money;

    public int getMoney() {
        return money;
    }

    public int getChange(int lottoCount, int lottoPrice) {
        int change = money - (lottoCount * lottoPrice);
        if(change <= 0) {
            return 0;
        }
        return change;
    }
}
