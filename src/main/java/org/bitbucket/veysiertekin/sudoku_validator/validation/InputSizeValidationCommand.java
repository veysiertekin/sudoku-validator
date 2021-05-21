package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.ApplicationMessage;
import org.bitbucket.veysiertekin.sudoku_validator.utils.Logger;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;

public class InputSizeValidationCommand implements ValidationCommand {
    private static final Logger logger = Logger.getInstance();

    public boolean validate(final Integer[][] input) {
        return checkSize(input);
    }

    private static boolean checkSize(final Integer[][] input) {
        final var length = getInputLength(input);
        final var result = length == BOARD_DIMENSION;
        if (!result)
            logger.error(ApplicationMessage.INVALID_BOARD_HEIGHT, length);
        return result;
    }

    private static int getInputLength(Integer[][] input) {
        return input == null ? 0 : input.length;
    }
}
