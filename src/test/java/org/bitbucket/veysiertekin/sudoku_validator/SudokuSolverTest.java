package org.bitbucket.veysiertekin.sudoku_validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SudokuSolverTest {
    @ParameterizedTest
    @DisplayName("Given input, When it is valid, Then it should return true")
    @MethodSource("validInput")
    void checkInputFormat(Integer[][] input, Optional<Integer[][]> expectedResult) {
        Optional<Integer[][]> result = new SudokuSolver().solve(input);
        assertThat(result.isPresent()).isEqualTo(expectedResult.isPresent());
        if (result.isPresent() && expectedResult.isPresent()) {
            var rows = result.get();
            var expectedRows = expectedResult.get();

            for (int i = 0; i < rows.length; i++) {
                assertThat(rows[i])
                        .as("row: " + i)
                        .isEqualTo(expectedRows[i]);
            }
        }
    }

    static Stream<Arguments> validInput() {
        return Stream.of(
                // Valid input
                Arguments.of(new Integer[][]{
                        new Integer[]{5, 3, 4, 6, 7, 8, 9, 1, null},
                        new Integer[]{6, 7, 2, 1, 9, 5, 3, null, 8},
                        new Integer[]{1, 9, 8, 3, 4, 2, null, 6, 7},
                        new Integer[]{8, 5, 9, 7, 6, null, 4, 2, 3},
                        new Integer[]{4, 2, 6, 8, null, 3, 7, 9, 1},
                        new Integer[]{7, 1, 3, null, 2, 4, 8, 5, 6},
                        new Integer[]{9, 6, null, 5, 3, 7, 2, 8, 4},
                        new Integer[]{2, null, 7, 4, 1, 9, 6, 3, 5},
                        new Integer[]{null, 4, 5, 2, 8, 6, 1, 7, 9}
                }, Optional.of(CommonTestConstants.VALID_BOARD_RESULT)),
                Arguments.of(CommonTestConstants.VALID_BOARD, Optional.of(CommonTestConstants.VALID_BOARD_RESULT))
        );
    }
}