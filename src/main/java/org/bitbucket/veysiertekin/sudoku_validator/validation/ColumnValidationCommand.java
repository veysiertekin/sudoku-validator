package org.bitbucket.veysiertekin.sudoku_validator.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;

public class ColumnValidationCommand implements ValidationCommand {
    @Override
    public boolean validate(List<List<Integer>> input) {
        return IntStream.range(0, BOARD_DIMENSION)
                .allMatch(columnIndex -> checkValuesNotRepeated(getColumnList(input, columnIndex)));
    }

    private List<Integer> getColumnList(final List<List<Integer>> input, final Integer columnIndex) {
        return input.stream().map(row -> row.get(columnIndex)).collect(Collectors.toList());
    }

    private static boolean checkValuesNotRepeated(List<Integer> integers) {
        final List<Integer> nonNullValues = integers.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        final HashSet<Integer> nonDuplicated = new HashSet<>(nonNullValues);
        return nonNullValues.size() == nonDuplicated.size();
    }
}
