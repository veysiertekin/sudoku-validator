package org.bitbucket.veysiertekin.sudoku_validator.sudoku;

import org.bitbucket.veysiertekin.sudoku_validator.CommonTestConstants;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SudokuBoardSerializerTest {
    @Test
    void serializeBoard() {
        var result = new SudokuBoardSerializer()
                .serializeAsString(CommonTestConstants.VALID_BOARD_RESULT);
        assertThat(result).isEqualTo("534|678|912\n" +
                "672|195|348\n" +
                "198|342|567\n" +
                "-----------\n" +
                "859|761|423\n" +
                "426|853|791\n" +
                "713|924|856\n" +
                "-----------\n" +
                "961|537|284\n" +
                "287|419|635\n" +
                "345|286|179");
    }
}
