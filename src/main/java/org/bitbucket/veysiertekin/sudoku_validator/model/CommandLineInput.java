package org.bitbucket.veysiertekin.sudoku_validator.model;

public class CommandLineInput {
    private final String fileName;

    public CommandLineInput(final String fileName) {
        this.fileName = fileName;
    }

    public String fileName() {
        return fileName;
    }
}
