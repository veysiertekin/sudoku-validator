package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.ApplicationMessage;
import org.bitbucket.veysiertekin.sudoku_validator.utils.Logger;

import java.util.Objects;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;
import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.EMPTY_FIELD;

public class InputFormatValidationCommand implements ValidationCommand {
    private static final Logger logger = Logger.getInstance();

    private static final int MIN_VAL = 1;
    private static final int MAX_VAL = 9;

    public boolean validate(final Integer[][] input) {
        return checkNestedSizes(input)
                && checkNumericValue(input);
    }

    private boolean checkNestedSizes(final Integer[][] input) {
        return checkSize(input)
                && checkRowSizes(input);
    }

    private boolean checkRowSizes(Integer[][] input) {
        for (int rowNumber = 0; rowNumber < input.length; rowNumber++) {
            if (!checkSize(input[rowNumber], rowNumber)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkNumericValue(final Integer[][] input) {
        for (int rowNumber = 0; rowNumber < input.length; rowNumber++) {
            for (int columnNumber = 0; columnNumber < input[rowNumber].length; columnNumber++) {
                final var data = input[rowNumber][columnNumber];
                if (!Objects.equals(data, EMPTY_FIELD)
                        && (data < MIN_VAL || data > MAX_VAL)) {
                    logger.error(ApplicationMessage.OUT_OF_RANGE_DATA_VALUE, data, rowNumber, columnNumber);
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkSize(final Integer[][] input) {
        final var length = getInputLength(input);
        final var result = length == BOARD_DIMENSION;
        if (!result)
            logger.error(ApplicationMessage.INVALID_BOARD_HEIGHT, length);
        return result;
    }

    private static boolean checkSize(final Integer[] input, final int rowNumber) {
        final var length = getInputLength(input);
        final var result = length == BOARD_DIMENSION;
        if (!result)
            logger.error(ApplicationMessage.INVALID_BOARD_WIDTH, rowNumber, length);
        return result;
    }

    private static int getInputLength(Integer[][] input) {
        return input == null ? 0 : input.length;
    }

    private static int getInputLength(Integer[] input) {
        return input == null ? 0 : input.length;
    }
}
