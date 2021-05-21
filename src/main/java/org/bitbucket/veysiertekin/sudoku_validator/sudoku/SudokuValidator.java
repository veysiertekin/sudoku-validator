package org.bitbucket.veysiertekin.sudoku_validator.sudoku;

import org.bitbucket.veysiertekin.sudoku_validator.validation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Performs visual sudoku validations
 */
public class SudokuValidator {
    private final List<ValidationCommand> validators;

    public SudokuValidator() {
        validators = new ArrayList<>();
        this.validators.add(new InputSizeValidationCommand());
        this.validators.add(new RowValidationCommand());
        this.validators.add(new ColumnValidationCommand());
        this.validators.add(new BoxValidationCommand());
    }

    /**
     * Performs {@link InputSizeValidationCommand}, {@link RowValidationCommand},
     * {@link ColumnValidationCommand} and {@link BoxValidationCommand} validations on given input
     *
     * @param input Sudoku board to be checked
     * @return if given input visually valid returns {@code true}, otherwise {@code false}
     */
    public boolean isBoardValid(final Integer[][] input) {
        return validators.stream().allMatch(v -> v.validate(input));
    }
}
