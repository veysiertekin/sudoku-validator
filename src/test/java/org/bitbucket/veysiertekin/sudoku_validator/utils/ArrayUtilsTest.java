package org.bitbucket.veysiertekin.sudoku_validator.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ArrayUtilsTest {
    @Test
    void contains() {
        final var input = new Integer[]{1, 2, 3};
        final var expected = 1;
        final var notExpected = 9;
        assertThat(ArrayUtils.contains(input, expected)).isTrue();
        assertThat(ArrayUtils.contains(input, notExpected)).isFalse();
    }

    @Test
    void arrayCopy() {
        var input = new Integer[][]{{0}};
        var copy = ArrayUtils.copyWrapper(input);
        assertThat(copy).isEqualTo(copy);
    }
}
