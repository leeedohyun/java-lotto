package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;

import org.junit.jupiter.api.Test;

class LottoWinningNumberTest {

    @Test
    void 당첨_번호에_보너스_번호가_포함되면_예외가_발생한다() {
        final WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        final LottoNumber lottoNumber = new LottoNumber(1);

        assertThatIllegalArgumentException().isThrownBy(() -> new LottoWinningNumber(winningNumbers, lottoNumber));
    }


    @Test
    void 로또_번호가_당첨_번호와_일치하는_숫자의_개수가_6개이면_1등이다() {
        // given
        final Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        final WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        final LottoNumber lottoNumber = new LottoNumber(7);
        final LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(winningNumbers, lottoNumber);

        // when
        final Rank rank = lottoWinningNumber.match(lotto);

        // then
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    void 로또_번호가_당첨_번호와_일치하는_숫자의_개수가_5개이면서_보너스_번호도_일치하면_2등이다() {
        // given
        final Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        final WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 7));
        final LottoNumber lottoNumber = new LottoNumber(6);
        final LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(winningNumbers, lottoNumber);

        // when
        final Rank rank = lottoWinningNumber.match(lotto);

        // then
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    void 로또_번호가_당첨_번호와_일치하는_숫자의_개수가_5개이면서_보너스_번호가_일치하지_않으면면_3등이다() {
        // given
        final Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        final WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 7));
        final LottoNumber lottoNumber = new LottoNumber(9);
        final LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(winningNumbers, lottoNumber);

        // when
        final Rank rank = lottoWinningNumber.match(lotto);

        // then
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @Test
    void 로또_번호가_당첨_번호와_일치하는_숫자의_개수가_4개이면_4등이다() {
        // given
        final Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        final WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 7, 8));
        final LottoNumber lottoNumber = new LottoNumber(10);
        final LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(winningNumbers, lottoNumber);

        // when
        final Rank rank = lottoWinningNumber.match(lotto);

        // then
        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @Test
    void 로또_번호가_당첨_번호와_일치하는_숫자의_개수가_3개이면_5등이다() {
        // given
        final Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        final WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 7, 8, 9));
        final LottoNumber lottoNumber = new LottoNumber(10);
        final LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(winningNumbers, lottoNumber);

        // when
        final Rank rank = lottoWinningNumber.match(lotto);

        // then
        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @Test
    void 로또_번호가_당첨_번호와_일치하는_숫자의_개수가_3개미만이면_등수가_없다() {
        // given
        final Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        final WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 7, 8, 9, 10));
        final LottoNumber lottoNumber = new LottoNumber(15);
        final LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(winningNumbers, lottoNumber);

        // when
        final Rank rank = lottoWinningNumber.match(lotto);

        // then
        assertThat(rank).isEqualTo(Rank.NONE);
    }
}
