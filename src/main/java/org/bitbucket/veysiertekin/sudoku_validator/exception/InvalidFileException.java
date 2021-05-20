package org.bitbucket.veysiertekin.sudoku_validator.exception;

public class InvalidFileException extends RuntimeException {
    public InvalidFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
