package view;

import java.math.BigDecimal;
import java.util.List;

import model.Lotto;
import model.Rank;
import model.WinningDetails;

public class OutputView {

    public void printLottoPurchaseCount(final int lottoPurchaseCount, final int numberOfManualLottoNumbers) {
        System.out.println("\n수동으로 " + numberOfManualLottoNumbers +"장, 자동으로 "+ lottoPurchaseCount+"개를 구매했습니다.");
    }

    public void printPurchaseLottos(final List<Lotto> lottos) {
        lottos.forEach(lotto -> System.out.println(lotto.getNumbers()));
    }

    public void printWinningStatistics(final WinningDetails winningDetails, final BigDecimal profit) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        System.out.println(Rank.FIFTH.getMatchingCount() + "개 일치 (" + Rank.FIFTH.getAmount() + "원) - "
                + winningDetails.getWinningCount(Rank.FIFTH) + "개");
        System.out.println(Rank.FOURTH.getMatchingCount() + "개 일치 (" + Rank.FOURTH.getAmount() + "원) - "
                + winningDetails.getWinningCount(Rank.FOURTH) + "개");
        System.out.println(Rank.THIRD.getMatchingCount() + "개 일치 (" + Rank.THIRD.getAmount() + "원) - "
                + winningDetails.getWinningCount(Rank.THIRD) + "개");
        System.out.println(
                Rank.SECOND.getMatchingCount() + "개 일치, 5개 일치, 보너스 볼 일치 (" + Rank.SECOND.getAmount() + "원) - "
                        + winningDetails.getWinningCount(Rank.SECOND) + "개");
        System.out.println(Rank.FIRST.getMatchingCount() + "개 일치 (" + Rank.FIRST.getAmount() + "원) - "
                + winningDetails.getWinningCount(Rank.FIRST) + "개");
        System.out.println("총 수익률은 " + profit + "입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
    }
}