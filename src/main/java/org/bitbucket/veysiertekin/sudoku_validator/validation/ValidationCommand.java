package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuBoard;

/**
 * Command contract between visual validations
 */
public interface ValidationCommand {
    /**
     * Performs visually Sudoku validation (does not look if it is solvable)
     *
     * @param input Sudoku board
     * @return {@code true} If it is valid, otherwise {@code false}
     */
    boolean validate(final SudokuBoard input);
}
