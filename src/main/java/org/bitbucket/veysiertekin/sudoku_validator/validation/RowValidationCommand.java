package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.utils.ArrayUtils;

public class RowValidationCommand implements ValidationCommand {
    @Override
    public boolean validate(Integer[][] input) {
        for (var value : input) {
            if (!ArrayUtils.checkValuesNotRepeatedExceptNulls(value)) {
                return false;
            }
        }
        return true;
    }
}
