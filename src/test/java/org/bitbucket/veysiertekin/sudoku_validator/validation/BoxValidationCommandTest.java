package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.CommonTestConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BoxValidationCommandTest {
    @ParameterizedTest
    @DisplayName("Given sudoku board, When it abides by the closest box rules, Then it should return true")
    @MethodSource("inputs")
    void checkInputFormat(List<List<Integer>> input, Boolean expectedResult) {
        boolean result = new BoxValidationCommand().validate(input);
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    @Test
    void extractBox() {
        var input = Arrays.asList(
                Arrays.asList(null, 7, 3, 1, 2, 3),
                Arrays.asList(9, 5, 6, 2, 4, 5),
                Arrays.asList(2, 1, 4, 7, null, 9)
        );
        var expectedBox = Arrays.asList(1, 2, 3, 2, 4, 5, 7, null, 9);
        int row = 0, column = 1;
        List<Integer> result = new BoxValidationCommand().extractBox(input, row, column);
        assertThat(result).isEqualTo(expectedBox);
    }

    static Stream<Arguments> inputs() {
        return Stream.of(
                // First box has repeated value: 1
                Arguments.of(Arrays.asList(
                        Arrays.asList(null, 7, 3),
                        Arrays.asList(1, 5, 6),
                        Arrays.asList(2, 1, 4)
                ), false),
                // Second box has repeated value: 2
                Arguments.of(Arrays.asList(
                        Arrays.asList(null, 7, 3, 1, 2, 3),
                        Arrays.asList(9, 5, 6, 2, 4, 5),
                        Arrays.asList(2, 1, 4, 7, null, 9)
                ), false),
                // Valid
                Arguments.of(CommonTestConstants.VALID_BOARD, true)
        );
    }
}