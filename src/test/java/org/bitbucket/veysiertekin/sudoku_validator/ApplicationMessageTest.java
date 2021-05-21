package org.bitbucket.veysiertekin.sudoku_validator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationMessageTest {
    @ParameterizedTest
    @MethodSource("messages")
    void formatMessage(final String expectedResult, final ApplicationMessage message, final Object[] parameters) {
        assertThat(message.format(parameters))
                .isEqualTo(expectedResult);
    }

    private static Stream<Arguments> messages() {
        return Stream.of(
                Arguments.of(
                        "Given file can not be read! File path: TEST",
                        ApplicationMessage.INVALID_FILE_INPUT,
                        new Object[]{"TEST"}
                ),
                Arguments.of(
                        "Invalid line count for given input size: 5",
                        ApplicationMessage.INVALID_FILE_SIZE,
                        new Object[]{5}
                ),
                Arguments.of(
                        "+Input does not match desired format!\n> Expected format: FORMAT\n> Line number: 1\n> Input: INPUT",
                        ApplicationMessage.MALFORMED_LINE,
                        new Object[]{"FORMAT", 1, "INPUT"}
                )
        );
    }
}
