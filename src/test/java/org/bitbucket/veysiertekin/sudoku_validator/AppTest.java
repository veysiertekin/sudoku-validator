package org.bitbucket.veysiertekin.sudoku_validator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemErrAndOut;
import static org.assertj.core.api.Assertions.assertThat;

class AppTest {
    @ParameterizedTest
    @MethodSource("existedFileInputs")
    void existedFileTests(final String[] args, final String message) throws Exception {
        var log = tapSystemErrAndOut(() -> App.main(args));
        assertThat(log.trim()).isEqualTo(message);
    }

    private static Stream<Arguments> existedFileInputs() {
        return Stream.of(
                Arguments.of(new String[]{"src/test/resources/data/01-valid-input.csv"}, "VALID"),
                Arguments.of(
                        new String[]{"src/test/resources/data/02-marformed-input.csv"},
                        "+Input does not match desired format!\n" +
                                "> Expected format: ([1-9]?,){8}[1-9]?\n" +
                                "> Line number: 1\n" +
                                "> Input: -,3,,,7,,,,\n" +
                                "INVALID"
                ),
                Arguments.of(
                        new String[]{"src/test/resources/data/05-visually-valid-but-unsolvable.csv"},
                        "Board visually valid but it is unsolvable!\n" +
                                "INVALID"
                )
        );
    }
}
