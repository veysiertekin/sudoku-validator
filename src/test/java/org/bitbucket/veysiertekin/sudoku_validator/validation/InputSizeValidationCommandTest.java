package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class InputSizeValidationCommandTest {

    @ParameterizedTest
    @DisplayName("Given input format, When it is invalid, Then it should return false")
    @MethodSource("inputs")
    void checkInputFormat(final SudokuBoard input, final Boolean expectedResult) {
        final boolean result = new InputSizeValidationCommand().validate(input);
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    static Stream<Arguments> inputs() {
        return Stream.of(
                // Null
                Arguments.of(new SudokuBoard(null), false),
                // Empty
                Arguments.of(new SudokuBoard(new Integer[][]{}), false),
                // Invalid size
                Arguments.of(new SudokuBoard(new Integer[][]{{1}}), false),
                // Valid formatted input, null for empty fields
                Arguments.of(new SudokuBoard(new Integer[][]{
                        new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 9},
                        new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 9},
                        new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 9},
                        new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 9},
                        new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 9},
                        new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 9},
                        new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 9},
                        new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 9},
                        new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 9}
                }), true)
        );
    }
}
