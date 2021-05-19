package org.bitbucket.veysiertekin.sudoku_validator;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class SudokuValidatorTest {
    @ParameterizedTest
    @DisplayName("Given input, When it is valid, Then it should return true")
    @MethodSource("validInput")
    void checkInputFormat(Integer[][] input, Boolean expectedResult) {
        boolean result = new SudokuValidator().validateAll(input);
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    static Stream<Arguments> validInput() {
        return Stream.of(
                // Valid input
                Arguments.of(CommonTestConstants.VALID_BOARD, true),
                Arguments.of(CommonTestConstants.VALID_BOARD_RESULT, true)
        );
    }
}
