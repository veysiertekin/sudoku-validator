package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.utils.ArrayUtils;

import java.util.stream.IntStream;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;

public class ColumnValidationCommand implements ValidationCommand {
    @Override
    public boolean validate(Integer[][] input) {
        return IntStream.range(0, BOARD_DIMENSION)
                .allMatch(columnIndex -> ArrayUtils.checkValuesNotRepeatedExceptNulls(getWholeColumn(input, columnIndex)));
    }

    public Integer[] getWholeColumn(final Integer[][] input, final Integer columnIndex) {
        var columnData = new Integer[BOARD_DIMENSION];
        var columnDataCursor = 0;
        for (var row : input) {
            columnData[columnDataCursor] = row[columnIndex];
            columnDataCursor++;
        }
        return columnData;
    }
}
