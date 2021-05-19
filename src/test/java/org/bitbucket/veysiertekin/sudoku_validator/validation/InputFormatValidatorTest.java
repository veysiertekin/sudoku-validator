package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class InputFormatValidatorTest {

    @ParameterizedTest
    @DisplayName("Given input format, When it is invalid, Then it should return false")
    @MethodSource("invalidFormattedInput")
    void checkInputFormat(List<List<Integer>> input, Boolean expectedResult) {
        boolean result = new InputFormatValidator().validate(input);
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    static Stream<Arguments> invalidFormattedInput() {
        return Stream.of(
                // Null
                Arguments.of(null, false),
                // Empty
                Arguments.of(Collections.emptyList(), false),
                // Invalid numeric range
                Arguments.of(Collections.singletonList(Collections.singletonList(0)), false),
                Arguments.of(Collections.singletonList(Collections.singletonList(10)), false),
                // Invalid size
                Arguments.of(Collections.singletonList(Collections.singletonList(1)), false),
                // Valid formatted input, null for empty fields
                Arguments.of(Arrays.asList(
                        Arrays.asList(null, 1, 2, 3, 4, 5, 6, 7, 9),
                        Arrays.asList(null, 1, 2, 3, 4, 5, 6, 7, 9),
                        Arrays.asList(null, 1, 2, 3, 4, 5, 6, 7, 9),
                        Arrays.asList(null, 1, 2, 3, 4, 5, 6, 7, 9),
                        Arrays.asList(null, 1, 2, 3, 4, 5, 6, 7, 9),
                        Arrays.asList(null, 1, 2, 3, 4, 5, 6, 7, 9),
                        Arrays.asList(null, 1, 2, 3, 4, 5, 6, 7, 9),
                        Arrays.asList(null, 1, 2, 3, 4, 5, 6, 7, 9),
                        Arrays.asList(null, 1, 2, 3, 4, 5, 6, 7, 9)
                ), true)
        );
    }
}