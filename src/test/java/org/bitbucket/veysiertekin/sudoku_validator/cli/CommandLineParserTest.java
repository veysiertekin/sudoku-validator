package org.bitbucket.veysiertekin.sudoku_validator.cli;

import org.bitbucket.veysiertekin.sudoku_validator.exception.InvalidCliArgumentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandLineParserTest {
    @Test
    void validFileTest() {
        var args = new String[]{"src/test/resources/data/01-valid-input.csv"};
        var result = new CommandLineParser(args)
                .parse();
        assertThat(result)
                .hasFieldOrPropertyWithValue("fileName", "src/test/resources/data/01-valid-input.csv");
    }

    @ParameterizedTest
    @MethodSource("messages")
    void cliExceptions(final String[] args, final String message) {
        assertThatThrownBy(() -> new CommandLineParser(args).parse())
                .isInstanceOf(InvalidCliArgumentException.class)
                .hasMessage(message);
    }

    private static Stream<Arguments> messages() {
        return Stream.of(
                Arguments.of(new String[]{}, "Expected arguments: 1, given: 0"),
                Arguments.of(new String[]{"invalid/path"}, "File does not exists! File path: invalid/path")
        );
    }
}
