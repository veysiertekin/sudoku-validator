package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.utils.ListUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;

public class ColumnValidationCommand implements ValidationCommand {
    @Override
    public boolean validate(List<List<Integer>> input) {
        return IntStream.range(0, BOARD_DIMENSION)
                .allMatch(columnIndex -> ListUtils.checkValuesNotRepeatedExceptNulls(getWholeColumn(input, columnIndex)));
    }

    private List<Integer> getWholeColumn(final List<List<Integer>> input, final Integer columnIndex) {
        return input.stream().map(row -> row.get(columnIndex)).collect(Collectors.toList());
    }
}
