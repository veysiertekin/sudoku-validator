package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuCell;
import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuCells;

import java.util.stream.IntStream;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;

/**
 * Performs row validations on given Sudoku board
 */
public class RowValidationCommand implements ValidationCommand {
    private final ValidationHelper helper = ValidationHelper.getInstance();

    @Override
    public boolean validate(Integer[][] input) {
        return IntStream.range(0, BOARD_DIMENSION)
                .allMatch(rowIndex ->
                        helper.containsDistinctValues(
                                ValidationType.ROW,
                                getWholeColumn(input, rowIndex)
                        ));
    }

    public SudokuCells getWholeColumn(final Integer[][] input, final Integer rowIndex) {
        var columnData = new SudokuCells(BOARD_DIMENSION);
        for (int columnIndex = 0; columnIndex < input[0].length; columnIndex++) {
            columnData.add(new SudokuCell(input[rowIndex][columnIndex], rowIndex, columnIndex));
        }
        return columnData;
    }
}
