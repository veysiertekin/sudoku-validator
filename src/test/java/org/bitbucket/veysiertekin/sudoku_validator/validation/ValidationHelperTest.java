package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.CommonTestConstants;
import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuCell;
import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuCells;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ValidationHelperTest {

    @ParameterizedTest
    @DisplayName("Given list, When it contains duplicates, Then it should return false")
    @MethodSource("inputs")
    void checkInputFormat(final SudokuCells input, final Boolean expectedResult) {
        final boolean result = ValidationHelper.getInstance().containsDistinctValues(ValidationType.BOX, input);
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    static Stream<Arguments> inputs() {
        return Stream.of(
                // Repeated value
                Arguments.of(convertToSudokuCells(new Integer[]{null, 1, 1, 3, 4, 5, 6, 7, 9}), false),
                // Valid
                Arguments.of(convertToSudokuCells(CommonTestConstants.VALID_BOARD_SAMPLE[0]), true)
        );
    }

    @ParameterizedTest
    @DisplayName("Given index, When it can be added, Then it should return true")
    @MethodSource("indexAvailabilityInputs")
    void indexAvailability(final Integer[][] input, final Integer value, final Integer row,
                           final Integer column, final Boolean expectedResult) {
        boolean result = ValidationHelper.getInstance().canValueBeInsertedToEmptyIndex(input, value, row, column);
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> indexAvailabilityInputs() {
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

    private static SudokuCells convertToSudokuCells(Integer[] data) {
        var result = new SudokuCells(data.length);
        for (int i = 0; i < data.length; i++) {
            result.add(new SudokuCell(data[i], 0, i));
        }
        return result;
    }

}