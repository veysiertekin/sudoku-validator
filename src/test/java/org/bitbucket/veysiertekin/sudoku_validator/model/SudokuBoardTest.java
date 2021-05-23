package org.bitbucket.veysiertekin.sudoku_validator.model;

import org.bitbucket.veysiertekin.sudoku_validator.CommonTestConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SudokuBoardTest {
    @Test
    void serializeBoard() {
        var result = CommonTestConstants.VALID_BOARD_RESULT.toString();
        assertThat(result).isEqualTo("534|678|912\n" +
                "672|195|348\n" +
                "198|342|567\n" +
                "-----------\n" +
                "859|761|423\n" +
                "426|853|791\n" +
                "713|924|856\n" +
                "-----------\n" +
                "961|537|284\n" +
                "287|419|635\n" +
                "345|286|179");
    }

    @ParameterizedTest
    @DisplayName("Given index, When it can be added, Then it should return true")
    @MethodSource("indexAvailabilityInputs")
    void indexAvailability(final SudokuBoard input, final Integer value, final Integer row,
                           final Integer column, final Boolean expectedResult) {
        boolean result = input.indexAvailable(row, column, value);
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> indexAvailabilityInputs() {
        return Stream.of(
                // Box contains
                Arguments.of(new SudokuBoard(new Integer[][]{
                        new Integer[]{5, 3, null},
                        new Integer[]{6, 7, 2},
                        new Integer[]{1, 9, 8}
                }), 3, 1, 1, false),
                // Row contains
                Arguments.of(new SudokuBoard(new Integer[][]{
                        new Integer[]{5, 3, 4, 6, 7, 8, 9, 1, null},
                        new Integer[]{6, 7, 2, 1, 9, 5, 3, null, 8},
                        new Integer[]{1, 9, 8, 3, 4, 2, null, 6, 7}
                }), 3, 0, 8, false),
                // Column contains
                Arguments.of(new SudokuBoard(new Integer[][]{
                        new Integer[]{5, 3, 4, 6, null, 8, 9, 1, null},
                        new Integer[]{6, 7, 2, 1, 9, 5, 3, null, 8},
                        new Integer[]{1, 9, 8, 3, 4, 2, null, 6, 7}
                }), 7, 0, 8, false),
                // Valid input for the index
                Arguments.of(new SudokuBoard(new Integer[][]{
                        new Integer[]{5, 3, 4, 6, null, 8, 9, 1, null},
                        new Integer[]{6, 7, 2, 1, 9, 5, 3, null, 8},
                        new Integer[]{1, 9, 8, 3, 4, 2, null, 6, 7}
                }), 2, 0, 8, true)
        );
    }
}
