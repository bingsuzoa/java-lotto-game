package domain.lottoMachine;

import domain.lottos.Lottos;

public record PurchaseResult(
        int manualCount,
        int autoCount,
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
