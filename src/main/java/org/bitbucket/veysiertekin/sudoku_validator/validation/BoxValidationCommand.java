package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuBoard;
import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuCells;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;

/**
 * Performs box validations on given Sudoku board
 */
public class BoxValidationCommand implements ValidationCommand {
    private final ValidationHelper helper = ValidationHelper.getInstance();

    @Override
    public boolean validate(SudokuBoard input) {
        for (int rowIndex = 0; rowIndex < BOARD_DIMENSION / 3; rowIndex++) {
            for (int columnIndex = 0; columnIndex < BOARD_DIMENSION / 3; columnIndex++) {
                var box = extractBox(input, rowIndex, columnIndex);
                if (!helper.containsDistinctValues(ValidationType.BOX, box))
                    return false;
            }
        }
        return true;
    }

    public SudokuCells extractBox(final SudokuBoard input, int rowIndex, int columnIndex) {
        var result = new SudokuCells(BOARD_DIMENSION);
        for (int row = rowIndex * 3; row < rowIndex * 3 + 3; row++) {
            for (int column = columnIndex * 3; column < columnIndex * 3 + 3; column++) {
                result.add(input.data(row, column));
            }
        }
        return result;
    }
}
