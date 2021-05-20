package org.bitbucket.veysiertekin.sudoku_validator.utils;

import org.bitbucket.veysiertekin.sudoku_validator.CommonTestConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ArrayUtilsTest {
    @ParameterizedTest
    @DisplayName("Given list, When it contains duplicates, Then it should return false")
    @MethodSource("inputs")
    void checkInputFormat(final Integer[] input, final Boolean expectedResult) {
        final boolean result = ArrayUtils.containsDistinctValuesExceptNulls(input);
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    static Stream<Arguments> inputs() {
        return Stream.of(
                // Repeated value
                Arguments.of(new Integer[]{null, 1, 1, 3, 4, 5, 6, 7, 9}, false),
                // Valid
                Arguments.of(CommonTestConstants.VALID_BOARD_SAMPLE[0], true)
        );
    }

    @Test
    void contains() {
        final var input = new Integer[]{1, 2, 3};
        final var expected = 1;
        final var notExpected = 9;
        assertThat(ArrayUtils.contains(input, expected)).isTrue();
        assertThat(ArrayUtils.contains(input, notExpected)).isFalse();
    }
}