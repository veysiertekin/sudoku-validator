package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuCell;
import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuCells;
import org.bitbucket.veysiertekin.sudoku_validator.utils.ArrayUtils;
import org.bitbucket.veysiertekin.sudoku_validator.utils.Logger;

import java.util.Objects;
import java.util.stream.Collectors;

import static org.bitbucket.veysiertekin.sudoku_validator.ApplicationMessage.DUPLICATED_DATA_POINTS;

/**
 * Contains common validation methods
 */
public class ValidationHelper {
    private final static Logger logger = Logger.getInstance();

    private ValidationHelper() {
    }

    private static class Holder {
        /**
         * Lazy singleton instance
         */
        private static final ValidationHelper INSTANCE = new ValidationHelper();
    }

    public static ValidationHelper getInstance() {
        return ValidationHelper.Holder.INSTANCE;
    }

    /**
     * This method used commonly at (row,column,box) validations.
     * Validates given 9 elements, logs if conflicted values detected with their location info.
     *
     * @param validationType type of validation (row,column,box)
     * @param group          items to be validated with their location points
     * @return {@code true} If non of the conflicted values detected, otherwise {@code false}
     * @see SudokuCell
     */
    public boolean containsDistinctValues(final ValidationType validationType, final SudokuCells cells) {
        final var result = cells.stream()
                .filter(cell -> !Objects.isNull(cell.data()))
                .collect(Collectors.groupingBy(SudokuCell::data));
        return result.values().stream()
                .filter(conflictedPoints -> conflictedPoints.size() > 1)
                .peek(points -> logDuplicatedPoints(validationType, new SudokuCells(points)))
                .count() == 0;
    }

    /**
     * Helper method for checking index availability for a potential value
     * As method name states, desired index must be `null`.
     *
     * @param input       sudoku board
     * @param value       value be added
     * @param rowIndex    `y` index of value
     * @param columnIndex `x` index of value
     * @return If the value suitable to be add desired index returns {@code true}, otherwise {@code false}
     */
    public boolean canValueBeInsertedToEmptyIndex(final Integer[][] input, final Integer value,
                                                  final int rowIndex, final int columnIndex) {
        return !boxContains(input, value, rowIndex, columnIndex)
                && !rowContains(input, value, rowIndex)
                && !columnContains(input, value, columnIndex);
    }

    private void logDuplicatedPoints(final ValidationType validationType, SudokuCells conflictedPoints) {
        var data = getDataFromNonEmptyList(conflictedPoints);
        var dataPoints = conflictedPoints.toLocationListString();
        logger.error(DUPLICATED_DATA_POINTS, validationType, data, dataPoints);
    }

    private int getDataFromNonEmptyList(SudokuCells conflictedPoints) {
        return conflictedPoints.get(0).data();
    }

    private boolean rowContains(final Integer[][] input, Integer value, Integer rowIndex) {
        return ArrayUtils.contains(input[rowIndex], value);
    }

    private boolean columnContains(Integer[][] input, Integer value, int columnIndex) {
        for (Integer[] integers : input)
            if (Objects.equals(integers[columnIndex], value))
                return true;
        return false;
    }

    private boolean boxContains(final Integer[][] input, Integer value, Integer rowIndex, Integer columnIndex) {
        final int boxRow = calculateBoxIndex(rowIndex);
        final int boxColumn = calculateBoxIndex(columnIndex);

        for (int row = boxRow; row < boxRow + 3; row++)
            for (int column = boxColumn; column < boxColumn + 3; column++)
                if (Objects.equals(input[row][column], value))
                    return true;
        return false;
    }

    /**
     * Fits index to power of 3; cleans remainder of division 3
     *
     * @param index index to fit power of 3
     * @return closest power of 3
     */
    private Integer calculateBoxIndex(int index) {
        return (index / 3) * 3;
    }
}
