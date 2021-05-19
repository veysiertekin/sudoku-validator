package org.bitbucket.veysiertekin.sudoku_validator;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class SudokuValidatorTest {

    @ParameterizedTest
    @DisplayName("Given input, When it is invalid, Then should return false")
    @MethodSource("invalidInput")
    void invalidFormattedInput_ShouldReturnFalse(List<Integer> input, Boolean expectedResult) {
        boolean result = new SudokuValidator().validate(input);
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    static Stream<Arguments> invalidInput() {
        return Stream.of(
                Arguments.of(null, false)
        );
    }
}
