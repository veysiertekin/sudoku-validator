package org.bitbucket.veysiertekin.sudoku_validator.exception;

import org.bitbucket.veysiertekin.sudoku_validator.ApplicationMessage;

/**
 * This exception designed to be used for csv file reading validation stage
 */
public class InvalidCsvFormatException extends SudokuRuntimeException {
    public InvalidCsvFormatException(ApplicationMessage message, Object... parameters) {
        super(message, parameters);
    }
}
