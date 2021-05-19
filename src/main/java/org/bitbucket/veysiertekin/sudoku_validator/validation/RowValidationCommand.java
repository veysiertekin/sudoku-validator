package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.utils.ListUtils;

import java.util.List;

public class RowValidationCommand implements ValidationCommand {
    @Override
    public boolean validate(List<List<Integer>> input) {
        return input.stream().allMatch(ListUtils::checkValuesNotRepeatedExceptNulls);
    }
}
