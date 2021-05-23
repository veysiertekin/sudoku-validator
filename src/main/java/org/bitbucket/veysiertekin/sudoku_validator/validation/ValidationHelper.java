package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuCell;
import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuCells;
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
                .map(SudokuCells::new)
                .peek(points -> logDuplicatedPoints(validationType, points))
                .count() == 0;
    }

    private void logDuplicatedPoints(final ValidationType validationType, final SudokuCells conflictedPoints) {
        var data = getDataFromNonEmptyList(conflictedPoints);
        var dataPoints = conflictedPoints.toLocationListString();
        logger.error(DUPLICATED_DATA_POINTS, validationType, data, dataPoints);
    }

    private int getDataFromNonEmptyList(SudokuCells conflictedPoints) {
        return conflictedPoints.get(0).data();
    }
}
