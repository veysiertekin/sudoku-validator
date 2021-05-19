package org.bitbucket.veysiertekin.sudoku_validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SudokuSolverTest {
    @ParameterizedTest
    @DisplayName("Given input, When it is valid, Then it should return true")
    @MethodSource("validInput")
    void checkInputFormat(List<List<Integer>> input, Optional<List<List<Integer>>> expectedResult) {
        Optional<List<List<Integer>>> result = new SudokuSolver().solve(input);
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    static Stream<Arguments> validInput() {
        return Stream.of(
                // Valid input
                Arguments.of(Arrays.asList(
                        Arrays.asList(5, 3, 4, 6, 7, 8, 9, 1, null),
                        Arrays.asList(6, 7, 2, 1, 9, 5, 3, null, 8),
                        Arrays.asList(1, 9, 8, 3, 4, 2, null, 6, 7),
                        Arrays.asList(8, 5, 9, 7, 6, null, 4, 2, 3),
                        Arrays.asList(4, 2, 6, 8, null, 3, 7, 9, 1),
                        Arrays.asList(7, 1, 3, null, 2, 4, 8, 5, 6),
                        Arrays.asList(9, 6, null, 5, 3, 7, 2, 8, 4),
                        Arrays.asList(2, null, 7, 4, 1, 9, 6, 3, 5),
                        Arrays.asList(null, 4, 5, 2, 8, 6, 1, 7, 9)
                ), Optional.of(CommonTestConstants.VALID_BOARD_RESULT))
        );
    }
}