package org.bitbucket.veysiertekin.sudoku_validator;

public enum ApplicationMessage {
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
    STATUS_VALID("VALID");

    private final String messageFormat;

    ApplicationMessage(final String messageFormat) {
        this.messageFormat = messageFormat;
    }

    public String format(Object... parameters) {
        return String.format(messageFormat, parameters);
    }
}
