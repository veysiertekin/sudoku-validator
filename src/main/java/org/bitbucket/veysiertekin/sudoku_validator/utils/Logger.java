package org.bitbucket.veysiertekin.sudoku_validator.utils;

import org.bitbucket.veysiertekin.sudoku_validator.ApplicationMessage;
import org.bitbucket.veysiertekin.sudoku_validator.exception.SudokuRuntimeException;

import java.io.PrintStream;

public class Logger {
    private Logger() {
    }

    private static class Holder {
        private static final Logger INSTANCE = new Logger();
    }

    public static Logger getInstance() {
        return Holder.INSTANCE;
    }

    public void error(final SudokuRuntimeException exception) {
        printToStream(System.err, exception.getMessage());
    }

    public void error(ApplicationMessage message, Object... parameters) {
        printToStream(System.err, message.format(parameters));
    }

    public void info(ApplicationMessage message, Object... parameters) {
        printToStream(System.out, message.format(parameters));
    }

    private void printToStream(PrintStream stream, String format) {
        stream.println(format);
    }
}
