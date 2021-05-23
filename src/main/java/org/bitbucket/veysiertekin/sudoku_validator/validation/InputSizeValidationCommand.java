package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.ApplicationMessage;
import org.bitbucket.veysiertekin.sudoku_validator.file.CsvLoader;
import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuBoard;
import org.bitbucket.veysiertekin.sudoku_validator.utils.Logger;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;

/**
 * Performs input row size validations.
 * Does not check row content, since row format will be validated
 * by {@linkplain CsvLoader} at reading stage
 */
public class InputSizeValidationCommand implements ValidationCommand {
    private static final Logger logger = Logger.getInstance();

    public boolean validate(final SudokuBoard input) {
        return checkSize(input);
    }

    private static boolean checkSize(final SudokuBoard input) {
        final var numberOfRows = input.numberOfRows();
        final var result = numberOfRows == BOARD_DIMENSION;
        if (!result)
            logger.error(ApplicationMessage.INVALID_BOARD_HEIGHT, numberOfRows);
        return result;
    }
}
