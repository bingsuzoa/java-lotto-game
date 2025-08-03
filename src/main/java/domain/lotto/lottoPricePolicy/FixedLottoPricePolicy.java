package domain.lotto.lottoPricePolicy;

public class FixedLottoPricePolicy implements LottoPricePolicy {

    private static final int LOTTO_PRICE = 1000;

    @Override
    public int getLottoPrice() {
        return LOTTO_PRICE;
    }
}
