package org.bitbucket.veysiertekin.sudoku_validator.exception;

import org.bitbucket.veysiertekin.sudoku_validator.ApplicationMessage;

public class InvalidFileException extends SudokuRuntimeException {
    public InvalidFileException(Throwable cause, String fileName) {
        super(cause, ApplicationMessage.INVALID_FILE_INPUT, fileName);
    }
}
