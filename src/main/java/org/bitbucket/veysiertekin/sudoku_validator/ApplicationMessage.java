package org.bitbucket.veysiertekin.sudoku_validator;

public enum ApplicationMessage {
    // Validator messages
    DUPLICATED_DATA_POINTS("Duplicates values has been found on same %s. Duplicated value: %s, Conflicted data points: %s"),
    // Cli error messages
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
    UNSOLVABLE_PUZZLE("Board visually valid but it is unsolvable!");

    private final String messageFormat;

    ApplicationMessage(final String messageFormat) {
        this.messageFormat = messageFormat;
    }

    public String format(Object... parameters) {
        return String.format(messageFormat, parameters);
    }
}
