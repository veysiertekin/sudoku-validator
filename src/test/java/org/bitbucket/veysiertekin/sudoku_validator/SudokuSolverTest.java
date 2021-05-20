package org.bitbucket.veysiertekin.sudoku_validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SudokuSolverTest {

    @Nested
    @DisplayName("input validation")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    final class InputValidation {

        @ParameterizedTest
        @DisplayName("Given input, When it is valid, Then it should return true")
        @MethodSource("inputs")
        void check(final Integer[][] input, final Optional<Integer[][]> expectedResult) {
            Optional<Integer[][]> result = new SudokuSolver().solve(input);
            assertThat(result.isPresent()).isEqualTo(expectedResult.isPresent());
            if (result.isPresent() && expectedResult.isPresent()) {
                assertNested(result.get(), expectedResult.get());
            }
        }

        Stream<Arguments> inputs() {
            return Stream.of(
                    // Invalid input; duplicated '3' at third box
                    Arguments.of(new Integer[][]{
                            new Integer[]{5, 3, 4, 6, 7, 8, 9, 1, null},
                            new Integer[]{6, 7, 2, 1, 9, 5, 3, null, 8},
                            new Integer[]{1, 9, 8, 3, 4, 2, null, 3, 7},
                            new Integer[]{8, 5, 9, 7, 6, null, 4, 2, 3},
                            new Integer[]{4, 2, 6, 8, null, 3, 7, 9, 1},
                            new Integer[]{7, 1, 3, null, 2, 4, 8, 5, 6},
                            new Integer[]{9, 6, null, 5, 3, 7, 2, 8, 4},
                            new Integer[]{2, null, 7, 4, 1, 9, 6, 3, 5},
                            new Integer[]{null, 4, 5, 2, 8, 6, 1, 7, 9}
                    }, Optional.empty()),
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
                    Arguments.of(CommonTestConstants.VALID_BOARD_SAMPLE, Optional.of(CommonTestConstants.VALID_BOARD_RESULT))
            );
        }

        private void assertNested(final Integer[][] rows, final Integer[][] expectedRows) {
            for (int i = 0; i < rows.length; i++) {
                assertThat(rows[i])
                        .as("row: " + i)
                        .isEqualTo(expectedRows[i]);
            }
        }
    }

    @Nested
    @DisplayName("index availability")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    final class IndexAvailability {

        @ParameterizedTest
        @DisplayName("Given index, When it can be added, Then it should return true")
        @MethodSource("inputs")
        void check(final Integer[][] input, final Integer value, final Integer row,
                   final Integer column, final Boolean expectedResult) {
            boolean result = new SudokuSolver().canValueBeInsertedToEmptyIndex(input, value, row, column);
            assertThat(result).isEqualTo(expectedResult);
        }

        Stream<Arguments> inputs() {
            return Stream.of(
                    // Box contains
                    Arguments.of(new Integer[][]{
                            new Integer[]{5, 3, null},
                            new Integer[]{6, 7, 2},
                            new Integer[]{1, 9, 8}
                    }, 3, 1, 1, false),
                    // Row contains
                    Arguments.of(new Integer[][]{
                            new Integer[]{5, 3, 4, 6, 7, 8, 9, 1, null},
                            new Integer[]{6, 7, 2, 1, 9, 5, 3, null, 8},
                            new Integer[]{1, 9, 8, 3, 4, 2, null, 6, 7}
                    }, 3, 0, 8, false),
                    // Column contains
                    Arguments.of(new Integer[][]{
                            new Integer[]{5, 3, 4, 6, null, 8, 9, 1, null},
                            new Integer[]{6, 7, 2, 1, 9, 5, 3, null, 8},
                            new Integer[]{1, 9, 8, 3, 4, 2, null, 6, 7}
                    }, 7, 0, 8, false),
                    // Valid input for the index
                    Arguments.of(new Integer[][]{
                            new Integer[]{5, 3, 4, 6, null, 8, 9, 1, null},
                            new Integer[]{6, 7, 2, 1, 9, 5, 3, null, 8},
                            new Integer[]{1, 9, 8, 3, 4, 2, null, 6, 7}
                    }, 2, 0, 8, true)
            );
        }
    }
}