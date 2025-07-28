import controller.LottoController;
import domain.lottoNumberGenerator.LottoNumberGenerator;
import domain.lottoNumberGenerator.RandomNumberGenerator;
import service.LottoService;

public class Main {


    public static void main(String[] args) {
        LottoNumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoService lottoService = new LottoService(numberGenerator);
        LottoController lottoController = new LottoController(lottoService);
        lottoController.startGame();
    }
}
