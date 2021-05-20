package org.bitbucket.veysiertekin.sudoku_validator.validation;

import java.util.Arrays;
import java.util.Objects;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;
import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.EMPTY_FIELD;

public class InputFormatValidationCommand implements ValidationCommand {
    private static final int MIN_VAL = 1;
    private static final int MAX_VAL = 9;

    public boolean validate(final Integer[][] input) {
        return checkNestedSizes(input)
                && checkNumericValue(input);
    }

    private boolean checkNestedSizes(final Integer[][] input) {
        return checkSize(input)
                && Arrays.stream(input).allMatch(InputFormatValidationCommand::checkSize);
    }

    private boolean checkNumericValue(final Integer[][] input) {
        for (var row : input) {
            for (var element : row) {
                if (!Objects.equals(element, EMPTY_FIELD)
                        && (element < MIN_VAL || element > MAX_VAL)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkSize(final Integer[][] input) {
        return input != null && input.length == BOARD_DIMENSION;
    }

    private static boolean checkSize(final Integer[] input) {
        return input != null && input.length == BOARD_DIMENSION;
    }
}
