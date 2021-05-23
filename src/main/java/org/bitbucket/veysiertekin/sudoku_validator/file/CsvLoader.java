package org.bitbucket.veysiertekin.sudoku_validator.file;

import org.bitbucket.veysiertekin.sudoku_validator.ApplicationMessage;
import org.bitbucket.veysiertekin.sudoku_validator.exception.InvalidCsvFormatException;
import org.bitbucket.veysiertekin.sudoku_validator.model.CsvOption;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;
import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.EMPTY_FIELD;

/**
 * Handles csv loading process, accepts a filename and a row validation
 */
public class CsvLoader {
    public static final String DELIMITER = ",";
    private static final String EMPTY_STRING = "";
    private static final int DISABLED_THRESHOLD = -1;

    /**
     * Desired line format: optional digits between 8 comma.
     */
    private final Pattern linePattern;
    /**
     * Csv line separation pattern
     */
    private final Pattern splitPattern = Pattern.compile(DELIMITER);

    private final FileLoader fileLoader;

    public CsvLoader(final CsvOption csvOption) {
        this.fileLoader = new FileLoader(csvOption.fileName());
        this.linePattern = csvOption.csvLinePattern();
    }

    /**
     * Reads the given file and converts to desired format
     *
     * @return Integer series of every row from file
     */
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
