package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.CommonTestConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemErr;
import static org.assertj.core.api.Assertions.assertThat;

class ColumnValidationCommandTest {
    @ParameterizedTest
    @DisplayName("Given sudoku board, When it abides by the column rules, Then it should return true")
    @MethodSource("inputs")
    void checkInputFormat(final Integer[][] input, final Boolean expectedResult, String expectedLog) throws Exception {
        var errorLog = tapSystemErr(() -> {
            final boolean result = new ColumnValidationCommand().validate(input);
            assertThat(result).isEqualTo(expectedResult);
        });
        assertThat(errorLog.trim()).isEqualTo(expectedLog);
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
                }, false, "Duplicates values has been found on same COLUMN. Duplicated value: 1, Conflicted data points: (1, 0), (2, 0)"),
                // Valid
                Arguments.of(CommonTestConstants.VALID_BOARD_SAMPLE, true, "")
        );
    }
}
