package org.bitbucket.veysiertekin.sudoku_validator.file;

import org.bitbucket.veysiertekin.sudoku_validator.CommonTestConstants;
import org.bitbucket.veysiertekin.sudoku_validator.exception.InvalidCsvFormatException;
import org.bitbucket.veysiertekin.sudoku_validator.factory.CsvOptionFactory;
import org.bitbucket.veysiertekin.sudoku_validator.model.CommandLineInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CsvLoaderTest {
    @Test
    void loadValidFile() {
        final var testFile = "src/test/resources/data/01-valid-input.csv";
        var lines = loadCsvFile(testFile);
        for (int i = 0; i < CommonTestConstants.VALID_BOARD_SAMPLE.length; i++) {
            assertThat(lines[i])
                    .isEqualTo(CommonTestConstants.VALID_BOARD_SAMPLE[i]);
        }
    }

    @ParameterizedTest
    @MethodSource("malformedFiles")
    void loadInvalidFile(final String fileName, final String expectedMessage) {
        assertThatThrownBy(() -> loadCsvFile(fileName))
                .isInstanceOf(InvalidCsvFormatException.class)
                .hasMessage(expectedMessage);
    }

    private static Stream<Arguments> malformedFiles() {
        return Stream.of(
                Arguments.of(
                        "src/test/resources/data/02-marformed-input.csv",
                        "+Input does not match desired format!\n" +
                                "> Expected format: ([1-9]?,){8}[1-9]?\n" +
                                "> Line number: 1\n" +
                                "> Input: -,3,,,7,,,,"
                ),
                Arguments.of(
                        "src/test/resources/data/03-invalid-input.csv",
                        "+Input does not match desired format!\n" +
                                "> Expected format: ([1-9]?,){8}[1-9]?\n" +
                                "> Line number: 5\n" +
                                "> Input: 4,,,8,"
                ),
                Arguments.of(
                        "src/test/resources/data/04-invalid-line-count.csv",
                        "Invalid line count for given input size: 2"
                )
        );
    }

    private Integer[][] loadCsvFile(String fileName) {
        return new CsvLoader(CsvOptionFactory.createSudokuCsvOptionFrom(new CommandLineInput(fileName))).load();
    }
}