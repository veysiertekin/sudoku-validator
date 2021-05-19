package org.bitbucket.veysiertekin.sudoku_validator;

import org.bitbucket.veysiertekin.sudoku_validator.validation.ColumnValidationCommand;
import org.bitbucket.veysiertekin.sudoku_validator.validation.InputFormatValidationCommand;
import org.bitbucket.veysiertekin.sudoku_validator.validation.RowValidationCommand;
import org.bitbucket.veysiertekin.sudoku_validator.validation.ValidationCommand;

import java.util.ArrayList;
import java.util.List;

public class SudokuValidator {
    private final List<ValidationCommand> validators;

    public SudokuValidator() {
        validators = new ArrayList<>();
        this.validators.add(new InputFormatValidationCommand());
        this.validators.add(new RowValidationCommand());
        this.validators.add(new ColumnValidationCommand());
    }

    public boolean validateAll(final List<List<Integer>> input) {
        return validators.stream().allMatch(v -> v.validate(input));
    }
}
