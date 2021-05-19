package org.bitbucket.veysiertekin.sudoku_validator;

import org.bitbucket.veysiertekin.sudoku_validator.validation.InputFormatValidationCommand;

import java.util.List;

public class SudokuValidator {
    private final InputFormatValidationCommand formatValidator = new InputFormatValidationCommand();

    public boolean validate(final List<List<Integer>> input) {
        return formatValidator.validate(input);
    }
}
