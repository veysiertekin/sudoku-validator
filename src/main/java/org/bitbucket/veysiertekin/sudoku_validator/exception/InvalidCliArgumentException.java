package org.bitbucket.veysiertekin.sudoku_validator.exception;

import org.bitbucket.veysiertekin.sudoku_validator.ApplicationMessage;

/**
 * This exception designed to be used for cli parameter checks
 */
public class InvalidCliArgumentException extends SudokuRuntimeException {
    public InvalidCliArgumentException(ApplicationMessage message, Object... parameters) {
        super(message, parameters);
    }
}
