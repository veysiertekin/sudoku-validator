package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuCell;

import java.util.ArrayList;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;

/**
 * Performs box validations on given Sudoku board
 */
public class BoxValidationCommand implements ValidationCommand {
    @Override
    public boolean validate(Integer[][] input) {
        for (int rowIndex = 0; rowIndex < BOARD_DIMENSION / 3; rowIndex++) {
            for (int columnIndex = 0; columnIndex < BOARD_DIMENSION / 3; columnIndex++) {
                var box = extractBox(input, rowIndex, columnIndex);
                if (!ValidationHelper.containsDistinctValues(ValidationType.BOX, box))
                    return false;
            }
        }
        return true;
    }

    public ArrayList<SudokuCell> extractBox(final Integer[][] input, int rowIndex, int columnIndex) {
        var result = new ArrayList<SudokuCell>(BOARD_DIMENSION);
        for (int row = rowIndex * 3; row < rowIndex * 3 + 3; row++) {
            for (int column = columnIndex * 3; column < columnIndex * 3 + 3; column++) {
                result.add(new SudokuCell(input[row][column], row, column));
            }
        }
        return result;
    }
}
