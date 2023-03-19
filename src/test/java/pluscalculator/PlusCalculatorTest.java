package pluscalculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import pluscalculator.domain.PlusCalculator;

public class PlusCalculatorTest {

    @ParameterizedTest(name = "{displayName} [{index}]: ''{argumentsWithNames}''")
    @CsvSource({"'11,55,44', 110",
            "'1,2,3', 6"})
    @DisplayName("쉼표를 포함한 문자열을 분리해서 계산한다.")
    void commaSplit(String plusExpression, int expectValue) {
        Assertions.assertThat(PlusCalculator.plusCalculate(plusExpression)).isEqualTo(expectValue);
    }

    @ParameterizedTest(name = "{displayName} [{index}]: ''{argumentsWithNames}''")
    @CsvSource({"'11:55:44', 110",
            "'1:2:3', 6"})
    @DisplayName("콜론을 포함한 문자열을 분리해서 계산한다.")
    void colonSplit(String plusExpression, int expectValue) {
        Assertions.assertThat(PlusCalculator.plusCalculate(plusExpression)).isEqualTo(expectValue);
    }

    @ParameterizedTest(name = "{displayName} [{index}]: ''{argumentsWithNames}''")
    @CsvSource({"'11,55:44', 110",
            "'1:2,3', 6"})
    @DisplayName("쉼표와 콜론을 포함한 문자열을 분리해서 계산한다.")
    void colonCommaSplit(String plusExpression, int expectValue) {
        Assertions.assertThat(PlusCalculator.plusCalculate(plusExpression)).isEqualTo(expectValue);
    }

    @ParameterizedTest(name = "{displayName} [{index}]: ''{argumentsWithNames}''")
    @ValueSource(strings = {" " })
    @NullSource
    @DisplayName("빈 문자열 또는 null을 입력할 경우 0을 반환해야 한다.")
    void nullValue(String plusExpression) {
        Assertions.assertThat(PlusCalculator.plusCalculate(plusExpression)).isZero();
    }

    @ParameterizedTest(name = "{displayName} [{index}]: ''{argumentsWithNames}''")
    @CsvSource({"'//;\n11;55;44', 110",
            "'//!\n11!55!44', 110"})
    @DisplayName("커스텀 구분자를 지정할 수 있다. (\"//\"와 \"\\n\" 사이에 값을 구분자로 지정한다.)")
    void customSplit(String plusExpression, int expectValue) {

        Assertions.assertThat(PlusCalculator.plusCalculate(plusExpression)).isEqualTo(expectValue);
    }

    @ParameterizedTest(name = "{displayName} [{index}]: ''{argumentsWithNames}''")
    @ValueSource(strings = {"//;\n11!55!44", "//!\n11*55*44"})
    @DisplayName("구분자를 제외하고 숫자이외의 값이 있으면 RuntimeException 예외를 throw 한다.")
    void withoutNumberException(String plusExpression) {
        Assertions.assertThatThrownBy(() -> PlusCalculator.plusCalculate(plusExpression)).isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest(name = "{displayName} [{index}]: ''{argumentsWithNames}''")
    @ValueSource(strings = {"//;\n-11;55;44", "//!\n11,55,-44"})
    @DisplayName("구분자를 제외하고 숫자이외의 값이 있으면 RuntimeException 예외를 throw 한다.")
    void minusContainException(String plusExpression) {
        Assertions.assertThatThrownBy(() -> PlusCalculator.plusCalculate(plusExpression)).isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest(name = "{displayName} [{index}]: ''{argumentsWithNames}''")
    @CsvSource({"'//;\n11.0;55.0;44.0', 110.0",
            "'//!\n11.5,55.5,44.0', 111"})
    @DisplayName("문자열에서 소수점은 허용한다.")
    void decimalPoint(String plusExpression, double expectValue) {
        Assertions.assertThat(PlusCalculator.plusCalculate(plusExpression)).isEqualTo(expectValue);
    }
}