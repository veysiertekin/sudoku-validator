package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.utils.ArrayUtils;

import java.util.Arrays;

public class RowValidationCommand implements ValidationCommand {
    @Override
    public boolean validate(Integer[][] input) {
        return Arrays.stream(input)
                .allMatch(ArrayUtils::containsDistinctValuesExceptNulls);
    }
}
