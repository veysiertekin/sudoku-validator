package org.bitbucket.veysiertekin.sudoku_validator;

import org.bitbucket.veysiertekin.sudoku_validator.validation.ValidationCommand;

import java.util.ArrayList;
import java.util.List;

public class SudokuValidator {
    private final List<ValidationCommand> validators = new ArrayList<>();

    public boolean validateAll(final List<List<Integer>> input) {
        return validators.stream().allMatch(v -> v.validate(input));
    }

    public SudokuValidator addValidator(final ValidationCommand validator) {
        validators.add(validator);
        return this;
    }
}
