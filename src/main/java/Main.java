import controller.LottoController;
import domain.lotto.lottoPricePolicy.FixedLottoPricePolicy;
import domain.lotto.lottoPricePolicy.LottoPricePolicy;
import domain.lottoNumberGenerator.LottoNumberGenerator;
import domain.lottoNumberGenerator.RandomNumberGenerator;
import service.LottoService;

public class Main {


    public static void main(String[] args) {
        LottoNumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoPricePolicy lottoPricePolicy = new FixedLottoPricePolicy();
        LottoService lottoService = new LottoService(numberGenerator, lottoPricePolicy);
        LottoController lottoController = new LottoController(lottoService);
        lottoController.getInputAndStartGame();
    }
}
