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
                Arguments.of(convertToSudokuCells(CommonTestConstants.VALID_BOARD_SAMPLE.row(0)), true)
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