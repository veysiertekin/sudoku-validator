package org.bitbucket.veysiertekin.sudoku_validator.validation;

import java.util.List;
import java.util.Objects;

public class InputFormatValidator {
    private static final int BOARD_SIZE = 81;
    private static final Integer EMPTY_FIELD = null;

    private static final int MIN_VAL = 1;
    private static final int MAX_VAL = 9;

    public boolean validate(final List<Integer> input) {
        return checkNonEmpty(input)
                && checkNumericValue(input)
                && checkSize(input);
    }

    private boolean checkNonEmpty(final List<Integer> input) {
        return input != null && !input.isEmpty();
    }

    private boolean checkNumericValue(final List<Integer> input) {
        return input.stream()
                .noneMatch(n -> !Objects.equals(n, EMPTY_FIELD) && (n < MIN_VAL || n > MAX_VAL));
    }

    private boolean checkSize(final List<Integer> input) {
        return input.size() == BOARD_SIZE;
    }
}
