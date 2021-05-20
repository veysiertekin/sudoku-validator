package org.bitbucket.veysiertekin.sudoku_validator;

import org.bitbucket.veysiertekin.sudoku_validator.exception.InvalidCsvFormatException;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.EMPTY_FIELD;

public class CsvLoader {

    private static final String EMPTY_STRING = "";
    private static final int DISABLED_THRESHOLD = -1;

    private final Pattern linePattern = Pattern.compile("([1-9]?,){8}[1-9]?");
    private final Pattern splitPattern = Pattern.compile(",");

    private final FileLoader fileLoader;

    public CsvLoader(final String fileName) {
        this.fileLoader = new FileLoader(fileName);
    }

    public Integer[][] load() {
        var lines = fileLoader.load();
        validateCsvInput(lines);
        return Arrays.stream(lines)
                .map(this::parseLine)
                .toArray(Integer[][]::new);
    }

    private Integer[] parseLine(String line) {
        return Arrays.stream(splitPattern.split(line, DISABLED_THRESHOLD))
                .map(this::convertInput)
                .toArray(Integer[]::new);
    }

    private Integer convertInput(String s) {
        return Objects.equals(s, EMPTY_STRING) ?
                EMPTY_FIELD : (Integer) Integer.parseInt(s);
    }

    private void validateCsvInput(String[] lines) {
        final var malformed = Arrays.stream(lines)
                .anyMatch(Predicate.not(line -> linePattern.matcher(line).matches()));
        if (malformed) {
            throw new InvalidCsvFormatException("Data does not matches desired format!");
        }
    }
}
