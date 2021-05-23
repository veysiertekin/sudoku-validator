package org.bitbucket.veysiertekin.sudoku_validator.sudoku;

import org.bitbucket.veysiertekin.sudoku_validator.CommonTestConstants;
import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SudokuSolverTest {

    @ParameterizedTest
    @DisplayName("Given input, When it is valid, Then it should return true")
    @MethodSource("solverInputs")
    void solver(final SudokuBoard input, final Optional<SudokuBoard> expectedResult) {
        Optional<SudokuBoard> result = SudokuSolver.getInstance().solve(input);
        assertThat(result.isPresent()).isEqualTo(expectedResult.isPresent());
        if (result.isPresent() && expectedResult.isPresent()) {
            assertThat(result.get().toString()).isEqualTo(expectedResult.get().toString());
        }
    }

    Stream<Arguments> solverInputs() {
        return Stream.of(
                // Invalid input; duplicated '3' at third box
                Arguments.of(new SudokuBoard(new Integer[][]{
                        new Integer[]{5, 3, 4, 6, 7, 8, 9, 1, null},
                        new Integer[]{6, 7, 2, 1, 9, 5, 3, null, 8},
                        new Integer[]{1, 9, 8, 3, 4, 2, null, 3, 7},
                        new Integer[]{8, 5, 9, 7, 6, null, 4, 2, 3},
                        new Integer[]{4, 2, 6, 8, null, 3, 7, 9, 1},
                        new Integer[]{7, 1, 3, null, 2, 4, 8, 5, 6},
                        new Integer[]{9, 6, null, 5, 3, 7, 2, 8, 4},
                        new Integer[]{2, null, 7, 4, 1, 9, 6, 3, 5},
                        new Integer[]{null, 4, 5, 2, 8, 6, 1, 7, 9}
                }), Optional.empty()),
                // Valid input
                Arguments.of(new SudokuBoard(new Integer[][]{
                        new Integer[]{5, 3, 4, 6, 7, 8, 9, 1, null},
                        new Integer[]{6, 7, 2, 1, 9, 5, 3, null, 8},
                        new Integer[]{1, 9, 8, 3, 4, 2, null, 6, 7},
                        new Integer[]{8, 5, 9, 7, 6, null, 4, 2, 3},
                        new Integer[]{4, 2, 6, 8, null, 3, 7, 9, 1},
                        new Integer[]{7, 1, 3, null, 2, 4, 8, 5, 6},
                        new Integer[]{9, 6, null, 5, 3, 7, 2, 8, 4},
                        new Integer[]{2, null, 7, 4, 1, 9, 6, 3, 5},
                        new Integer[]{null, 4, 5, 2, 8, 6, 1, 7, 9}
                }), Optional.of(CommonTestConstants.VALID_BOARD_RESULT)),
                // Visually valid but unsolvable puzzle
                // this puzzle has been taken from: http://www.jibble.org/impossible-sudoku/
                Arguments.of(new SudokuBoard(new Integer[][]{
                        new Integer[]{null, 7, null, null, null, 6, null, null, null},
                        new Integer[]{9, null, null, null, null, null, null, 4, 1},
                        new Integer[]{null, null, 8, null, null, 9, null, 5, null},
                        new Integer[]{null, 9, null, null, null, 7, null, null, 2},
                        new Integer[]{null, null, 3, null, null, null, 8, null, null},
                        new Integer[]{4, null, null, 8, null, null, null, 1, null},
                        new Integer[]{null, 8, null, 3, null, null, 9, null, null},
                        new Integer[]{1, 6, null, null, null, null, null, null, 7},
                        new Integer[]{null, null, null, 5, null, null, null, 8, null}
                }), Optional.empty()),
                Arguments.of(CommonTestConstants.VALID_BOARD_SAMPLE, Optional.of(CommonTestConstants.VALID_BOARD_RESULT))
        );
    }
}
