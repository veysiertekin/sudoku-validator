package org.bitbucket.veysiertekin.sudoku_validator.validation;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;

public class RowValidationCommand implements ValidationCommand {
    @Override
    public boolean validate(Integer[][] input) {
        return IntStream.range(0, BOARD_DIMENSION)
                .allMatch(rowIndex ->
                        ValidationHelper.containsDistinctValues(
                                ValidationType.ROW,
                                getWholeColumn(input, rowIndex)
                        ));
    }

    public ArrayList<SudokuCell> getWholeColumn(final Integer[][] input, final Integer rowIndex) {
        var columnData = new ArrayList<SudokuCell>(BOARD_DIMENSION);
        for (int columnIndex = 0; columnIndex < input[0].length; columnIndex++) {
            columnData.add(new SudokuCell(input[rowIndex][columnIndex], rowIndex, columnIndex));
        }
        return columnData;
    }
}
