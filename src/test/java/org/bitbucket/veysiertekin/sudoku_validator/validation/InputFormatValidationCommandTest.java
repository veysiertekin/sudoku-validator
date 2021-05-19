package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class InputFormatValidationCommandTest {

    @ParameterizedTest
    @DisplayName("Given input format, When it is invalid, Then it should return false")
    @MethodSource("inputs")
    void checkInputFormat(Integer[][] input, Boolean expectedResult) {
        boolean result = new InputFormatValidationCommand().validate(input);
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    static Stream<Arguments> inputs() {
        return Stream.of(
                // Null
                Arguments.of(null, false),
                // Empty
                Arguments.of(new Integer[][]{}, false),
                // Invalid numeric range
                Arguments.of(new Integer[][]{{0}}, false),
                Arguments.of(new Integer[][]{{10}}, false),
                // Invalid size
                Arguments.of(new Integer[][]{{1}}, false),
                // Valid formatted input, null for empty fields
                Arguments.of(new Integer[][]{
                        new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 9},
                        new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 9},
                        new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 9},
                        new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 9},
                        new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 9},
                        new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 9},
                        new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 9},
                        new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 9},
                        new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 9}
                }, true)
        );
    }
}
