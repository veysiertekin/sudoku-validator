package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuBoard;
import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuCells;

import java.util.stream.IntStream;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;

/**
 * Performs row validations on given Sudoku board
 */
public class RowValidationCommand implements ValidationCommand {
    private final ValidationHelper helper = ValidationHelper.getInstance();

    @Override
    public boolean validate(SudokuBoard input) {
        return IntStream.range(0, BOARD_DIMENSION)
                .allMatch(rowIndex ->
                        helper.containsDistinctValues(
                                ValidationType.ROW,
                                getWholeColumn(input, rowIndex)
                        ));
    }

    public SudokuCells getWholeColumn(final SudokuBoard input, final Integer rowIndex) {
        var columnData = new SudokuCells(BOARD_DIMENSION);
        for (int columnIndex = 0; columnIndex < BOARD_DIMENSION; columnIndex++) {
            columnData.add(input.data(rowIndex, columnIndex));
        }
        return columnData;
    }
}
