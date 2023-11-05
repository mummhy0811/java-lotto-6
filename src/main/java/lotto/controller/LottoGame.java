package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.PurchaseManager;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGame {
    private static InputView inputView;
    private static OutputView outputView;
    private static PurchaseManager purchaseManager;
    private static Lottos lottos;
    private static WinningNumbers winningNumbers;

    public LottoGame(){
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void startGame(){
        purchase();
        makeLottos();
        getWinningNumbers();
    }
    private void purchase(){
        boolean validateInput=false;
        while(!validateInput){
            validateInput = validatePurchaseAmount(inputView.inputPurchaseAmount());
        }
        outputView.printResult(purchaseManager.getPurchaseNumber());
    }
    private boolean validatePurchaseAmount(String purchaseAmount){
        try {
            purchaseManager = new PurchaseManager(purchaseAmount);
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void makeLottos(){
        lottos = new Lottos(makeLotto());
        outputView.printLottos(lottos.getLottos());
    }
    private List<Lotto> makeLotto(){
        List<Lotto> lottos = new ArrayList<>();
        for(int i=0;i<purchaseManager.getPurchaseNumber();i++){
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Collections.sort(numbers);
            lottos.add(new Lotto(numbers));
        }
        return lottos;
    }
    private void getWinningNumbers(){
        boolean validateInput=false;
        while(!validateInput){
            validateInput = validateWinningNumber(inputView.inputWinningNumber());
        }
    }
    private boolean validateWinningNumber(String input){
        try {
            winningNumbers = new WinningNumbers(input);
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}
