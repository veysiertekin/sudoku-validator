package org.bitbucket.veysiertekin.sudoku_validator;

public enum ApplicationMessage {
    // Validator messages,
    INVALID_BOARD_HEIGHT("Invalid row count! Input row count: %s"),
    DUPLICATED_DATA_POINTS("Duplicated values have been found on same %s. Duplicated value: %s, Conflicted data points (x,y): %s"),
    // Cli error messages
    CLI_USAGE("Usage:\n $ ./validate.sh <path to file>\n\nExample:\n $ ./validate.sh src/test/resources/data/01-valid-input.csv\n"),
    INVALID_ARGUMENT_SIZE("Expected arguments: %s, given: %s"),
    INVALID_FILE_PATH("File does not exists! File path: %s"),
    // File loader error messages
    INVALID_FILE_INPUT("Given file can not be read! File path: %s"),
    // Csv loader error messages
    INVALID_FILE_SIZE("Invalid line count for given input size: %s"),
    MALFORMED_LINE("+Input does not match desired format!\n> Expected format: %s\n> Line number: %s\n> Input: %s"),
    // Main App
    STATUS_INVALID("INVALID"),
    STATUS_VALID("VALID"),
    // Sudoku solver
    UNSOLVABLE_PUZZLE("Board visually valid but it is unsolvable!"),
    SOLUTION_HAS_BEEN_FOUND("Solution has been found for given sudoku puzzle!\n\nResult:\n%s\n");

    private final String messageFormat;

    ApplicationMessage(final String messageFormat) {
        this.messageFormat = messageFormat;
    }

    public String format(Object... parameters) {
        return String.format(messageFormat, parameters);
    }
}
