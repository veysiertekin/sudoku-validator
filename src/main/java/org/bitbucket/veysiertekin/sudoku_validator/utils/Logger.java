package org.bitbucket.veysiertekin.sudoku_validator.utils;

import org.bitbucket.veysiertekin.sudoku_validator.ApplicationMessage;
import org.bitbucket.veysiertekin.sudoku_validator.exception.SudokuRuntimeException;

import java.io.PrintStream;

/**
 * Singleton logging utility.
 */
public class Logger {
    private Logger() {
    }

    private static class Holder {
        /**
         * Lazy singleton instance
         */
        private static final Logger INSTANCE = new Logger();
    }

    public static Logger getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Prints given exception to {@link System#err}
     *
     * @param exception accepts Sudoku exceptions as input
     */
    public void error(final SudokuRuntimeException exception) {
        printToStream(System.err, exception.getMessage());
    }

    /**
     * Prints given message to {@link System#err} after application of passed parameters
     *
     * @param message    System message format
     * @param parameters Message parameters if there is any.
     */
    public void error(ApplicationMessage message, Object... parameters) {
        printToStream(System.err, message.format(parameters));
    }

    /**
     * Prints given message to {@link System#out} after application of passed parameters
     *
     * @param message    System message format
     * @param parameters Message parameters if there is any.
     */
    public void info(ApplicationMessage message, Object... parameters) {
        printToStream(System.out, message.format(parameters));
    }

    private void printToStream(PrintStream stream, String format) {
        stream.println(format);
    }
}
