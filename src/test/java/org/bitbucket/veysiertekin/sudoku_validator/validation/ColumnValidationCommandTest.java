package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.CommonTestConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ColumnValidationCommandTest {
    @ParameterizedTest
    @DisplayName("Given sudoku board, When it abides by the column rules, Then it should return true")
    @MethodSource("inputs")
    void checkInputFormat(List<List<Integer>> input, Boolean expectedResult) {
        boolean result = new ColumnValidationCommand().validate(input);
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    static Stream<Arguments> inputs() {
        return Stream.of(
                // Repeated value
                Arguments.of(Arrays.asList(
                        Collections.singletonList(null),
                        Collections.singletonList(1),
                        Collections.singletonList(1),
                        Collections.singletonList(2),
                        Collections.singletonList(3),
                        Collections.singletonList(4),
                        Collections.singletonList(5),
                        Collections.singletonList(6),
                        Collections.singletonList(7)
                ), false),
                // Valid
                Arguments.of(CommonTestConstants.VALID_BOARD, true)
        );
    }
}