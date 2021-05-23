package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuCell;
import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuCells;

import java.util.stream.IntStream;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;

/**
 * Performs column validations on given Sudoku board
 */
public class ColumnValidationCommand implements ValidationCommand {
    private final ValidationHelper validationHelper = ValidationHelper.getInstance();

    @Override
    public boolean validate(Integer[][] input) {
        return IntStream.range(0, BOARD_DIMENSION)
                .allMatch(columnIndex ->
                        validationHelper.containsDistinctValues(
                                ValidationType.COLUMN,
                                getWholeColumn(input, columnIndex)
                        ));
    }

    public SudokuCells getWholeColumn(final Integer[][] input, final Integer columnIndex) {
        var columnData = new SudokuCells(BOARD_DIMENSION);
        for (int rowIndex = 0; rowIndex < input.length; rowIndex++) {
            columnData.add(new SudokuCell(input[rowIndex][columnIndex], rowIndex, columnIndex));
        }
        return columnData;
    }
}
