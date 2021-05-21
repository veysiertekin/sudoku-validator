package org.bitbucket.veysiertekin.sudoku_validator.cli;

import org.bitbucket.veysiertekin.sudoku_validator.ApplicationMessage;
import org.bitbucket.veysiertekin.sudoku_validator.exception.InvalidCliArgumentException;

import java.io.File;

public class CommandLineParser {
    private static final int EXPECTED_ARGUMENTS_SIZE = 1;

    private final String[] args;

    public CommandLineParser(String[] args) {
        this.args = args;
    }

    public CommandLineInput parse() {
        validateArgumentSize();
        validateFilePath();
        return new CommandLineInput(args[0]);
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
