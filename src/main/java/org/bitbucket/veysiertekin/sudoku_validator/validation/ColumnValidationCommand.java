package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuCell;

import java.util.ArrayList;
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

    public ArrayList<SudokuCell> getWholeColumn(final Integer[][] input, final Integer columnIndex) {
        var columnData = new ArrayList<SudokuCell>(BOARD_DIMENSION);
        for (int rowIndex = 0; rowIndex < input.length; rowIndex++) {
            columnData.add(new SudokuCell(input[rowIndex][columnIndex], rowIndex, columnIndex));
        }
        return columnData;
    }
}
