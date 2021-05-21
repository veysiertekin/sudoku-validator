package org.bitbucket.veysiertekin.sudoku_validator;

import org.bitbucket.veysiertekin.sudoku_validator.exception.SudokuRuntimeException;

import java.io.PrintStream;

public class Logger {
    private final PrintStream outStream = System.out;
    private final PrintStream errorStream = System.err;

    public void error(final SudokuRuntimeException exception) {
        printToStream(errorStream, exception.getMessage());
    }

    public void info(ApplicationMessage message, Object... parameters) {
        printToStream(outStream, message.format(parameters));
    }

    private void printToStream(PrintStream stream, String format) {
        stream.println(format);
    }
}
