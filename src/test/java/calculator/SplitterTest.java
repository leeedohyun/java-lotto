package calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class SplitterTest {

  @DisplayName("기본 구분자가 없는 문자열을 값을 그대로 반환한다")
  @Test
  void withoutDelim() {
    assertThat(Splitter.split("1")).containsExactly("1");
  }

  @DisplayName("기본 구분자로 나누어진 문자 리스트를 반환한다")
  @ParameterizedTest
  @MethodSource("withDelimProvider")
  void withDelim(String input, String... expected) {
    assertThat(Splitter.split(input)).containsExactly(expected);
  }

  static Stream<Arguments> withDelimProvider() {
    return Stream.of(
        arguments("a b", new String[]{"a", "b"}),
        arguments("a b c", new String[]{"a", "b", "c"})
    );
  }

  @DisplayName("매개변수에 전달된 인자가 null 또는 빈 문자열인 경우 IllegalArgument 예외를 던진다")
  @ParameterizedTest
  @NullAndEmptySource
  void exception(String input) {
    assertThatIllegalArgumentException().isThrownBy(() -> Splitter.split(input))
        .withMessage("나누려는 문자열을 입력하세요");
  }

  @DisplayName("커스텀 구분자로 나누어진 문자 리스트를 반환한다")
  @ParameterizedTest
  @MethodSource("withCustomDelimProvider")
  void withCustomDelim(String input, String delim, String... expected) {
    assertThat(Splitter.split(input, delim)).containsExactly(expected);
  }

  static Stream<Arguments> withCustomDelimProvider() {
    return Stream.of(
        arguments("a,b", ",", new String[]{"a", "b"}),
        arguments("a-b-c", "-", new String[]{"a", "b", "c"})
    );
  }

  @DisplayName("커스텀 구분자가 null 또는 빈 문자열인 경우 IllegalArgument 예외를 던진다")
  @ParameterizedTest
  @NullAndEmptySource
  void delimNullOrEmpty(String delim) {
    assertThatIllegalArgumentException().isThrownBy(() -> Splitter.split("1 2 3", delim))
        .withMessage("구분자를 입력하세요");
  }

  @DisplayName("splitAsList의 구분자가 null 또는 빈 문자열인 경우 IllegalArgument 예외를 던진다")
  @ParameterizedTest
  @NullAndEmptySource
  void splitAsListValueNullOrEmpty(String value) {
    assertThatIllegalArgumentException().isThrownBy(() -> Splitter.splitAsList(value, ","))
        .withMessage("나누려는 문자열을 입력하세요");
  }

  @DisplayName("splitAsList의 구분자가 null 또는 빈 문자열인 경우 IllegalArgument 예외를 던진다")
  @ParameterizedTest
  @NullAndEmptySource
  void splitAsListDelimNullOrEmpty(String delim) {
    assertThatIllegalArgumentException().isThrownBy(() -> Splitter.splitAsList("1,2,3", delim))
        .withMessage("구분자를 입력하세요");
  }

  @DisplayName("입력받은 문자열을 숫자 리스트로 반환한다")
  @ParameterizedTest
  @MethodSource("provideForSplitAsList")
  void splitAsList(String value, List<Integer> list) {
    assertThat(Splitter.splitAsList(value, ",")).isEqualTo(list);
  }

  private static Stream<Arguments> provideForSplitAsList() {
    return Stream.of(
        arguments("1,2,3,4", List.of(1, 2, 3, 4)),
        arguments("1", List.of(1))
    );
  }
}