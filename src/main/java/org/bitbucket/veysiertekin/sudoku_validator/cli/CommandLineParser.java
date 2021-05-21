package org.bitbucket.veysiertekin.sudoku_validator.cli;

import org.bitbucket.veysiertekin.sudoku_validator.ApplicationMessage;
import org.bitbucket.veysiertekin.sudoku_validator.exception.InvalidCliArgumentException;
import org.bitbucket.veysiertekin.sudoku_validator.exception.SudokuRuntimeException;
import org.bitbucket.veysiertekin.sudoku_validator.utils.Logger;

import java.io.File;

public class CommandLineParser {
    private static final Logger logger = Logger.getInstance();
    private static final int EXPECTED_ARGUMENTS_SIZE = 1;

    private final String[] args;

    public CommandLineParser(String[] args) {
        this.args = args;
    }

    /**
     * <pre>
     * Checks given arguments for:
     * - Argument size
     * - Is given file exist?
     *
     * @return validated file name
     */
    public CommandLineInput parse() {
        try {
            validateArgumentSize();
            validateFilePath();
            return new CommandLineInput(args[0]);
        } catch (SudokuRuntimeException e) {
            logger.error(ApplicationMessage.CLI_USAGE);
            throw e;
        }
    }

    private void validateArgumentSize() {
        var argumentSize = args.length;
        if (argumentSize != EXPECTED_ARGUMENTS_SIZE) {
            throw new InvalidCliArgumentException(
                    ApplicationMessage.INVALID_ARGUMENT_SIZE, EXPECTED_ARGUMENTS_SIZE, argumentSize
            );
        }
    }

    private void validateFilePath() {
        var filePath = args[0];
        if (!new File(filePath).exists()) {
            throw new InvalidCliArgumentException(
                    ApplicationMessage.INVALID_FILE_PATH, filePath
            );
        }
    }
}
