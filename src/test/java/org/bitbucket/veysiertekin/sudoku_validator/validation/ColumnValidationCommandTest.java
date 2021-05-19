package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.CommonTestConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ColumnValidationCommandTest {
    @ParameterizedTest
    @DisplayName("Given sudoku board, When it abides by the column rules, Then it should return true")
    @MethodSource("inputs")
    void checkInputFormat(Integer[][] input, Boolean expectedResult) {
        boolean result = new ColumnValidationCommand().validate(input);
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    static Stream<Arguments> inputs() {
        return Stream.of(
                // Repeated value
                Arguments.of(new Integer[][]{
                        new Integer[]{null},
                        new Integer[]{1},
                        new Integer[]{1},
                        new Integer[]{2},
                        new Integer[]{3},
                        new Integer[]{4},
                        new Integer[]{5},
                        new Integer[]{6},
                        new Integer[]{7}
                }, false),
                // Valid
                Arguments.of(CommonTestConstants.VALID_BOARD, true)
        );
    }
}