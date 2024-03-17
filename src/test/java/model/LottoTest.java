package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;

import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    void 로또_번호_개수가_6개초과하면_예외가_발생한다() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)));
    }

    @Test
    void 로또_번호_개수가_6개미만이면_예외가_발생한다() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)));
    }

    @Test
    void 로또_번호가_당첨_번호와_일치하는_숫자의_개수가_6개이면_1등이다() {
        // given
        final Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        final WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

        // when
        final Rank rank = lotto.match(winningNumbers);

        // then
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    void 로또_번호가_당첨_번호와_일치하는_숫자의_개수가_5개이면_2등이다() {
        // given
        final Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        final WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 7));

        // when
        final Rank rank = lotto.match(winningNumbers);

        // then
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    void 로또_번호가_당첨_번호와_일치하는_숫자의_개수가_4개이면_3등이다() {
        // given
        final Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        final WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 7, 8));

        // when
        final Rank rank = lotto.match(winningNumbers);

        // then
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @Test
    void 로또_번호가_당첨_번호와_일치하는_숫자의_개수가_3개이면_4등이다() {
        // given
        final Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        final WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 7, 8, 9));

        // when
        final Rank rank = lotto.match(winningNumbers);

        // then
        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @Test
    void 로또_번호가_당첨_번호와_일치하는_숫자의_개수가_3개미만이면_등수가_없다() {
        // given
        final Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        final WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 7, 8, 9, 10));

        // when
        final Rank rank = lotto.match(winningNumbers);

        // then
        assertThat(rank).isEqualTo(Rank.NONE);
    }
}
