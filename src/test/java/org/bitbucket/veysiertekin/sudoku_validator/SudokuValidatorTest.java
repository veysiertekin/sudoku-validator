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
    void checkInputFormat(List<List<Integer>> input, Boolean expectedResult) {
        boolean result = new SudokuValidator().validate(input);
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    static Stream<Arguments> validInput() {
        return Stream.of(
                // Valid input
                Arguments.of(Arrays.asList(
                        Arrays.asList(5, 3, null, null, 7, null, null, null, null),
                        Arrays.asList(6, null, null, 1, 9, 5, null, null, null),
                        Arrays.asList(null, 9, 8, null, null, null, null, 6, null),
                        Arrays.asList(8, null, null, null, 6, null, null, null, 3),
                        Arrays.asList(4, null, null, 8, null, 3, null, null, 1),
                        Arrays.asList(7, null, null, null, 2, null, null, null, 6),
                        Arrays.asList(null, 6, null, null, null, null, 2, 8, null),
                        Arrays.asList(null, null, null, 4, 1, 9, null, null, 5),
                        Arrays.asList(null, null, null, null, 8, null, null, 7, 9)
                ), true)
        );
    }
}
