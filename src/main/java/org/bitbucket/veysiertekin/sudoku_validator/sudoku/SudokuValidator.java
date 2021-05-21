package org.bitbucket.veysiertekin.sudoku_validator.sudoku;

import org.bitbucket.veysiertekin.sudoku_validator.validation.*;

import java.util.ArrayList;
import java.util.List;

public class SudokuValidator {
    private final List<ValidationCommand> validators;

    public SudokuValidator() {
        validators = new ArrayList<>();
        this.validators.add(new InputSizeValidationCommand());
        this.validators.add(new RowValidationCommand());
        this.validators.add(new ColumnValidationCommand());
        this.validators.add(new BoxValidationCommand());
    }

    public boolean isBoardValid(final Integer[][] input) {
        return validators.stream().allMatch(v -> v.validate(input));
    }
}
