package org.bitbucket.veysiertekin.sudoku_validator.model;

import java.util.regex.Pattern;

public class CsvOption {
    final String fileName;
    final Pattern csvLinePattern;

    public CsvOption(String fileName, Pattern pattern) {
        this.fileName = fileName;
        this.csvLinePattern = pattern;
    }

    public String fileName() {
        return fileName;
    }

    public Pattern csvLinePattern() {
        return csvLinePattern;
    }
}
