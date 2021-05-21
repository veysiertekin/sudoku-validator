package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.CommonTestConstants;
import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuCell;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BoxValidationCommandTest {
    @ParameterizedTest
    @DisplayName("Given sudoku board, When it abides by the closest box rules, Then it should return true")
    @MethodSource("inputs")
    void checkInputFormat(final Integer[][] input, final Boolean expectedResult) {
        final boolean result = new BoxValidationCommand().validate(input);
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    @Test
    void extractBox() {
        final var input = new Integer[][]{
                new Integer[]{null, 7, 3, 1, 2, 3},
                new Integer[]{9, 5, 6, 2, 4, 5},
                new Integer[]{2, 1, 4, 7, null, 9}
        };
        final var expectedBox = Arrays.asList(
                new SudokuCell(1, 0, 3),
                new SudokuCell(2, 0, 4),
                new SudokuCell(3, 0, 5),
                new SudokuCell(2, 1, 3),
                new SudokuCell(4, 1, 4),
                new SudokuCell(5, 1, 5),
                new SudokuCell(7, 2, 3),
                new SudokuCell(null, 2, 4),
                new SudokuCell(9, 2, 5)
        );
        final int row = 0, column = 1;
        List<SudokuCell> result = new BoxValidationCommand().extractBox(input, row, column);
        assertThat(collectCellAsString(result))
                .isEqualTo(collectCellAsString(expectedBox));
    }

    static Stream<Arguments> inputs() {
        return Stream.of(
                // First box has repeated value: 1
                Arguments.of(new Integer[][]{
                        new Integer[]{null, 7, 3},
                        new Integer[]{1, 5, 6},
                        new Integer[]{2, 1, 4}
                }, false),
                // Second box has repeated value: 2
                Arguments.of(new Integer[][]{
                        new Integer[]{null, 7, 3, 1, 2, 3},
                        new Integer[]{9, 5, 6, 2, 4, 5},
                        new Integer[]{2, 1, 4, 7, null, 9}
                }, false),
                // Valid
                Arguments.of(CommonTestConstants.VALID_BOARD_SAMPLE, true)
        );
    }

    private String collectCellAsString(List<SudokuCell> expectedBox) {
        return expectedBox.stream().map(SudokuCell::toString).collect(Collectors.joining());
    }
}