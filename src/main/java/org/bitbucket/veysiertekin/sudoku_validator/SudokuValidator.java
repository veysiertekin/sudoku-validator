package org.bitbucket.veysiertekin.sudoku_validator;

import org.bitbucket.veysiertekin.sudoku_validator.validation.InputFormatValidator;

import java.util.List;

public class SudokuValidator {
    private final InputFormatValidator formatValidator = new InputFormatValidator();

    public boolean validate(final List<List<Integer>> input) {
        return formatValidator.validate(input);
    }
}
