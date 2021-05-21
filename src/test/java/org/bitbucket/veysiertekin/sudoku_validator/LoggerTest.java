package org.bitbucket.veysiertekin.sudoku_validator;

import org.bitbucket.veysiertekin.sudoku_validator.exception.InvalidCsvFormatException;
import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.*;
import static org.assertj.core.api.Assertions.assertThat;

class LoggerTest {

    @Test
    void errorTest() throws Exception {
        var errorLog = tapSystemErrNormalized(() -> {
            new Logger().error(new InvalidCsvFormatException(ApplicationMessage.INVALID_FILE_SIZE, 1));
        });
        assertThat(errorLog.trim())
                .isEqualTo("Invalid line count for given input size: 1");
    }

    @Test
    void infoTest() throws Exception {
        var log = tapSystemOutNormalized(() -> {
            new Logger().info(ApplicationMessage.STATUS_VALID);
        });
        assertThat(log.trim()).isEqualTo("VALID");
    }
}