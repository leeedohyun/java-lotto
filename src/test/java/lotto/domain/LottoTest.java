package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    private Lotto lotto;

    @BeforeEach
    void setUp() {
        this.lotto = new Lotto(Arrays.asList(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6))
        );
    }

    @Test
    @DisplayName("Lotto 객체 값 비교")
    void lotto_object_isEqualTo() {
        //given
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6));

        //when
        Lotto lottery = new Lotto(lottoNumbers);

        //then
        assertThat(lottery).isEqualTo(lotto);
    }

    @Test
    @DisplayName("Lotto 로또 6개 초과생성")
    void lotto_OverSize_Exception() {
        assertThatThrownBy(() ->
                new Lotto(Arrays.asList(
                        LottoNumber.of(1),
                        LottoNumber.of(2),
                        LottoNumber.of(3),
                        LottoNumber.of(4),
                        LottoNumber.of(5),
                        LottoNumber.of(6),
                        LottoNumber.of(7),
                        LottoNumber.of(8)
                ))
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또번호가 6개초과");
    }


    @Test
    @DisplayName("Lotto 로또 6개 미만생성")
    void lotto_UnderSize_Exception() {
        assertThatThrownBy(() ->
                new Lotto(Arrays.asList(
                        LottoNumber.of(1),
                        LottoNumber.of(2),
                        LottoNumber.of(3)
                ))
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또번호가 6개미만");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,4,5,6:10:FIRST",
            "1,2,3,4,5,9:5:SECOND",
            "1,2,3,4,5,9:10:THIRD",
            "1,2,3,4,8,9:10:FOURTH",
            "1,2,3,7,8,9:10:FIFTH"
    }, delimiter = ':')
    @DisplayName("Lotto 당첨번호+보너스번호 등수 확인")
    void lotto_winningNumber_matchOfCount(String lottoNumber,String winningNumber, String rank) {
        //given
        LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(lottoNumber);

        //when
        LottoResult lottoResult = LottoResult.check(lotto,lottoWinningNumber,Integer.parseInt(winningNumber));

        //then
        assertThat(lottoResult).isEqualTo(LottoResult.valueOf(rank));
    }
}
