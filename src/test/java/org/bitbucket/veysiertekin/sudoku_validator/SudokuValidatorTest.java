package org.bitbucket.veysiertekin.sudoku_validator;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class SudokuValidatorTest {
    @ParameterizedTest
    @DisplayName("Given input, When it is valid, Then it should return true")
    @MethodSource("validInput")
    void checkInputFormat(List<Integer> input, Boolean expectedResult) {
        boolean result = new SudokuValidator().validate(input);
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    static Stream<Arguments> validInput() {
        return Stream.of(
                // Valid input
                Arguments.of(Arrays.asList(
                        5, 3, null, null, 7, null, null, null, null,
                        6, null, null, 1, 9, 5, null, null, null,
                        null, 9, 8, null, null, null, null, 6, null,
                        8, null, null, null, 6, null, null, null, 3,
                        4, null, null, 8, null, 3, null, null, 1,
                        7, null, null, null, 2, null, null, null, 6,
                        null, 6, null, null, null, null, 2, 8, null,
                        null, null, null, 4, 1, 9, null, null, 5,
                        null, null, null, null, 8, null, null, 7, 9
                ), true)
        );
    }
}
