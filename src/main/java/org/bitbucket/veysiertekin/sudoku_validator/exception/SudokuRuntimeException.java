package org.bitbucket.veysiertekin.sudoku_validator.exception;

import org.bitbucket.veysiertekin.sudoku_validator.ApplicationMessage;

/**
 * Generic exception interface & signature
 */
public abstract class SudokuRuntimeException extends RuntimeException {
    private final ApplicationMessage message;
    private final Object[] parameters;

    public SudokuRuntimeException(final ApplicationMessage message, final Object... parameters) {
        this.message = message;
        this.parameters = parameters;
    }

    public SudokuRuntimeException(final Throwable cause, final ApplicationMessage message, final Object... parameters) {
        super(cause);
        this.message = message;
        this.parameters = parameters;
    }

    @Override
    public String getMessage() {
        return message.format(parameters);
    }
}
