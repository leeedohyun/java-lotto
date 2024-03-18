package model;

import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers {

    private static final int MAX_WINNING_NUMBERS = 6;

    private final List<LottoNumber> numbers;

    public WinningNumbers(final List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public boolean contains(final LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    private void validate(final List<Integer> numbers) {
        if (numbers.size() != MAX_WINNING_NUMBERS) {
            throw new IllegalArgumentException("당첨 번호의 개수는 6개여야 합니다.");
        }
    }
}
