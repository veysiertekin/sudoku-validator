package org.bitbucket.veysiertekin.sudoku_validator.utils;

import org.bitbucket.veysiertekin.sudoku_validator.CommonTestConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ListUtilsTest {
    @ParameterizedTest
    @DisplayName("Given list, When it contains duplicates, Then it should return false")
    @MethodSource("inputs")
    void checkInputFormat(List<Integer> input, Boolean expectedResult) {
        boolean result = ListUtils.checkValuesNotRepeatedExceptNulls(input);
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    static Stream<Arguments> inputs() {
        return Stream.of(
                // Repeated value
                Arguments.of(Arrays.asList(null, 1, 1, 3, 4, 5, 6, 7, 9), false),
                // Valid
                Arguments.of(CommonTestConstants.VALID_BOARD.get(0), true)
        );
    }
}