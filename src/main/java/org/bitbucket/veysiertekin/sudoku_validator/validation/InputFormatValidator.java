package org.bitbucket.veysiertekin.sudoku_validator.validation;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class InputFormatValidator {
    private static final int DIMENSION = 9;
    private static final Integer EMPTY_FIELD = null;

    private static final int MIN_VAL = 1;
    private static final int MAX_VAL = 9;

    public boolean validate(final List<List<Integer>> input) {
        return checkNestedSize(input)
                && checkNumericValue(input);
    }

    private boolean checkNestedSize(final List<List<Integer>> input) {
        return checkSize(input)
                && input.stream().allMatch(InputFormatValidator::checkSize);
    }

    private boolean checkNumericValue(final List<List<Integer>> input) {
        return input.stream()
                .flatMap(Collection::stream)
                .noneMatch(n -> !Objects.equals(n, EMPTY_FIELD) && (n < MIN_VAL || n > MAX_VAL));
    }

    private static boolean checkSize(final List<?> input) {
        return input != null && input.size() == DIMENSION;
    }
}
