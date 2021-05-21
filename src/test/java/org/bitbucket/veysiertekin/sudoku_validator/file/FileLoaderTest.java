package org.bitbucket.veysiertekin.sudoku_validator.file;

import org.bitbucket.veysiertekin.sudoku_validator.exception.InvalidFileException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileLoaderTest {
    private static final String[] INPUT = new String[]{
            "5,3,,,7,,,,",
            "6,,,1,9,5,,,",
            ",9,8,,,,,6,",
            "8,,,,6,,,,3",
            "4,,,8,,3,,,1",
            "7,,,,2,,,,6",
            ",6,,,,,2,8,",
            ",,,4,1,9,,,5",
            ",,,,8,,,7,9"
    };

    @Test
    void loadValidFile() {
        final var testFile = "src/test/resources/data/01-valid-input.csv";
        final var lines = new FileLoader(testFile).load();
        for (int i = 0; i < INPUT.length; i++) {
            assertThat(lines[i]).isEqualTo(INPUT[i]);
        }
    }

    @Test
    void loadMissingFile() {
        assertThatThrownBy(() -> {
            final var testFile = "<non existing>";
            new FileLoader(testFile).load();
        }).isInstanceOf(InvalidFileException.class);
    }
}
