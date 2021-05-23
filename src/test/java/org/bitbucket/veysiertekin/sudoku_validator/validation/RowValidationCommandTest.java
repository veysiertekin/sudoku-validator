package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.CommonTestConstants;
import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RowValidationCommandTest {
    @ParameterizedTest
    @DisplayName("Given sudoku board, When it abides by the row rules, Then it should return true")
    @MethodSource("inputs")
    void checkInputFormat(final SudokuBoard input, final Boolean expectedResult) {
        final boolean result = new RowValidationCommand().validate(input);
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    static Stream<Arguments> inputs() {
        return Stream.of(
                // Repeated value
                Arguments.of(new SudokuBoard(new Integer[][]{new Integer[]{null, 1, 1, 3, 4, 5, 6, 7, 9}}), false),
                // Valid
                Arguments.of(CommonTestConstants.VALID_BOARD_SAMPLE, true)
        );
    }
}
