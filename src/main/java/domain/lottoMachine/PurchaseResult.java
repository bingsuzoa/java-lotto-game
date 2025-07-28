package domain.lottoMachine;

import domain.lottos.Lottos;

public record PurchaseResult(
        Lottos lottos,
        int change
) {

    public String getFormattedString() {
        return lottos.toFormattedString();
    }

    public int getLottoCount() {
        return lottos.getLottos().size();
    }

}
