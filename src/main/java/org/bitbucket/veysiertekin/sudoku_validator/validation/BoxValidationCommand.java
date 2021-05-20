package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.utils.ArrayUtils;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;

public class BoxValidationCommand implements ValidationCommand {
    @Override
    public boolean validate(Integer[][] input) {
        for (int rowIndex = 0; rowIndex < BOARD_DIMENSION / 3; rowIndex++) {
            for (int columnIndex = 0; columnIndex < BOARD_DIMENSION / 3; columnIndex++) {
                var box = extractBox(input, rowIndex, columnIndex);
                if (!ArrayUtils.containsDistinctValuesExceptNulls(box))
                    return false;
            }
        }
        return true;
    }

    public Integer[] extractBox(final Integer[][] input, int rowIndex, int columnIndex) {
        var result = new Integer[BOARD_DIMENSION];
        var resultCursor = 0;
        for (int row = rowIndex * 3; row < rowIndex * 3 + 3; row++) {
            System.arraycopy(input[row], columnIndex * 3, result, resultCursor, 3);
            resultCursor += 3;
        }
        return result;
    }
}
