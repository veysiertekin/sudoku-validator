package org.bitbucket.veysiertekin.sudoku_validator.sudoku;


import org.bitbucket.veysiertekin.sudoku_validator.CommonTestConstants;
import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class SudokuValidatorTest {
    @ParameterizedTest
    @DisplayName("Given input, When it is valid, Then it should return true")
    @MethodSource("inputs")
    void checkInputFormat(final SudokuBoard input, final Boolean expectedResult) {
        final boolean result = SudokuValidator.getInstance().isBoardValid(input);
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    static Stream<Arguments> inputs() {
        return Stream.of(
                // Valid input
                Arguments.of(CommonTestConstants.VALID_BOARD_SAMPLE, true),
                Arguments.of(CommonTestConstants.VALID_BOARD_RESULT, true)
        );
    }
}
