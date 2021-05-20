package org.bitbucket.veysiertekin.sudoku_validator;

import org.bitbucket.veysiertekin.sudoku_validator.exception.InvalidCsvFormatException;
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
        var lines = new CsvLoader(testFile).load();
        for (int i = 0; i < CommonTestConstants.VALID_BOARD_SAMPLE.length; i++) {
            assertThat(lines[i]).isEqualTo(CommonTestConstants.VALID_BOARD_SAMPLE[i]);
        }
    }

    @ParameterizedTest
    @MethodSource("malformedFiles")
    void loadInvalidFile(final String fileName) {
        assertThatThrownBy(() -> new CsvLoader(fileName).load())
                .isInstanceOf(InvalidCsvFormatException.class);
    }

    private static Stream<Arguments> malformedFiles() {
        return Stream.of(
                Arguments.of("src/test/resources/data/02-marformed-input.csv"),
                Arguments.of("src/test/resources/data/03-invalid-input.csv")
        );
    }
}