package domain.money;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class MoneyTest {

    @Test
    @DisplayName("로또를 구매할 수 있는 금액이라면 반환")
    void 로또_구매할_수_있다면_예외_없이_반환() {
        Money money = new Money(13000);
        Assertions.assertEquals(money.getMoney(), 13000);
    }

    @Test
    @DisplayName("로또를 구매할 수 있는 최소 금액 안되면 예외 발생")
    void 로또_구매할_수_없다면_예외_반환() {
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Money money = new Money(0);
        });
        Assertions.assertEquals(e.getMessage(), Money.INSUFFICIENT_MONEY_MESSAGE);
    }
}
