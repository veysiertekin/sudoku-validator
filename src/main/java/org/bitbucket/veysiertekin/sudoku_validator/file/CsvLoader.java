package org.bitbucket.veysiertekin.sudoku_validator.file;

import org.bitbucket.veysiertekin.sudoku_validator.ApplicationMessage;
import org.bitbucket.veysiertekin.sudoku_validator.exception.InvalidCsvFormatException;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;
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
        validateCsvLines(lines);
        var result = parseLines(lines);
        validateResultSize(result);
        return result;
    }

    private Integer[][] parseLines(String[] lines) {
        return Arrays.stream(lines)
                .map(this::parseLine)
                .toArray(Integer[][]::new);
    }

    private Integer[] parseLine(final String line) {
        return Arrays.stream(splitPattern.split(line, DISABLED_THRESHOLD))
                .map(this::convertInput)
                .toArray(Integer[]::new);
    }

    private Integer convertInput(final String s) {
        return Objects.equals(s, EMPTY_STRING) ?
                EMPTY_FIELD : (Integer) Integer.parseInt(s);
    }

    private void validateResultSize(final Integer[][] result) {
        if (result.length != BOARD_DIMENSION) {
            throw new InvalidCsvFormatException(
                    ApplicationMessage.INVALID_FILE_SIZE, result.length
            );
        }
    }

    private void validateCsvLines(final String[] lines) {
        IntStream.range(0, lines.length)
                .forEach(index -> validateCsvLine(lines[index], index + 1));
    }

    private void validateCsvLine(final String line, final Integer lineNumber) {
        if (!linePattern.matcher(line).matches()) {
            throw new InvalidCsvFormatException(
                    ApplicationMessage.MALFORMED_LINE,
                    linePattern.pattern(), lineNumber, line
            );
        }
    }
}
