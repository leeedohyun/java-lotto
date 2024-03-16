package model;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;

import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @Test
    void 당첨_번호_개수가_6개가_아니면_예외가_발생한다() {
        assertThatIllegalArgumentException().isThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6, 7)));
    }
}