package org.bitbucket.veysiertekin.sudoku_validator.validation;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;

public class ColumnValidationCommand implements ValidationCommand {
    @Override
    public boolean validate(Integer[][] input) {
        return IntStream.range(0, BOARD_DIMENSION)
                .allMatch(columnIndex ->
                        ValidationHelper.containsDistinctValues(
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
