package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomNumberGenerateTest {

    @Test
    @DisplayName("랜덤 숫자 6개가 생성되는지 확인한다")
    void randomNumberGenerateTest() {
        assertThat(RandomNumberGenerator.generate()).hasSize(6);
    }
}
