package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.utils.ListUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;

public class BoxValidationCommand implements ValidationCommand {
    @Override
    public boolean validate(List<List<Integer>> input) {
        for (int rowIndex = 0; rowIndex < BOARD_DIMENSION / 3; rowIndex++) {
            for (int columnIndex = 0; columnIndex < BOARD_DIMENSION / 3; columnIndex++) {
                var box = extractBox(input, rowIndex, columnIndex);
                if (!ListUtils.checkValuesNotRepeatedExceptNulls(box))
                    return false;
            }
        }
        return true;
    }

    public List<Integer> extractBox(final List<List<Integer>> input, int rowIndex, int columnIndex) {
        return input.subList(rowIndex * 3, rowIndex * 3 + 3)
                .stream()
                .map(row -> row.subList(columnIndex * 3, columnIndex * 3 + 3))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
