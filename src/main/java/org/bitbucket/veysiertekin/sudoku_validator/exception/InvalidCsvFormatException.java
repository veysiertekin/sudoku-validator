package org.bitbucket.veysiertekin.sudoku_validator.exception;

import org.bitbucket.veysiertekin.sudoku_validator.ApplicationMessage;

public class InvalidCsvFormatException extends SudokuRuntimeException {
    public InvalidCsvFormatException(ApplicationMessage message, Object... parameters) {
        super(message, parameters);
    }
}
