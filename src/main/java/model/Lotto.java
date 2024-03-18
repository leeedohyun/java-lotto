package model;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private static final int MAX_LOTTO_NUMBERS = 6;

    private final List<LottoNumber> numbers;

    public Lotto(final List<Integer> numbers) {
        validateLottoNumbers(numbers);
        this.numbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

    }

    public Rank match(final LottoWinningNumber lottoWinningNumber) {
        final int countOfMatch = findMatchNumberCount(lottoWinningNumber.getWinningNumbers());
        final boolean contains = numbers.contains(lottoWinningNumber.getLottoNumber());
        return Rank.determine(countOfMatch, contains);
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toList());
    }

    private void validateLottoNumbers(final List<Integer> numbers) {
        if (numbers.size() != MAX_LOTTO_NUMBERS) {
            throw new IllegalArgumentException("로또 번호의 개수는 6개여야 합니다.");
        }
    }

    private int findMatchNumberCount(final WinningNumbers winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .mapToInt(LottoNumber::getValue)
                .count();
    }
}
