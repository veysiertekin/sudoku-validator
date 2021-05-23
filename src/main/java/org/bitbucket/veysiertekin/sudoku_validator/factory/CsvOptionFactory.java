package org.bitbucket.veysiertekin.sudoku_validator.factory;

import org.bitbucket.veysiertekin.sudoku_validator.model.CommandLineInput;
import org.bitbucket.veysiertekin.sudoku_validator.model.CsvOption;

import java.util.regex.Pattern;

public class CsvOptionFactory {
    private static final Pattern sudokuLinePattern = Pattern.compile("([1-9]?,){8}[1-9]?");

    public static CsvOption createSudokuCsvOptionFrom(final CommandLineInput cliInput) {
        return new CsvOption(cliInput.fileName(), sudokuLinePattern);
    }
}
