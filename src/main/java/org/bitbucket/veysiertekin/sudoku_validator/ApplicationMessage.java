package org.bitbucket.veysiertekin.sudoku_validator;

public enum ApplicationMessage {
    // Error messages
    INVALID_FILE_INPUT("Given file can not be read! File path: %s"),
    INVALID_FILE_SIZE("Invalid line count for given input size: %s"),
    MALFORMED_LINE("+Input does not match desired format!\n> Expected format: %s\n> Line number: %s\n> Input: %s");

    private final String messageFormat;

    ApplicationMessage(final String messageFormat) {
        this.messageFormat = messageFormat;
    }

    public String format(Object... parameters) {
        return String.format(messageFormat, parameters);
    }
}
