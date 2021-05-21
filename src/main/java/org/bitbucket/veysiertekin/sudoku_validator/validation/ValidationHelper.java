package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuCell;
import org.bitbucket.veysiertekin.sudoku_validator.utils.Logger;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.bitbucket.veysiertekin.sudoku_validator.ApplicationMessage.DUPLICATED_DATA_POINTS;

/**
 * Contains common validation methods
 */
public class ValidationHelper {
    private final static Logger logger = Logger.getInstance();

    /**
     * This method used commonly at (row,column,box) validations.
     * Validates given 9 elements, logs if conflicted values detected with their location info.
     *
     * @param validationType type of validation (row,column,box)
     * @param group          items to be validated with their location points
     * @return {@code true} If non of the conflicted values detected, otherwise {@code false}
     * @see SudokuCell
     */
    public static boolean containsDistinctValues(final ValidationType validationType, final List<SudokuCell> group) {
        final var result = group.stream()
                .filter(cell -> !Objects.isNull(cell.data()))
                .collect(Collectors.groupingBy(SudokuCell::data));
        return result.values().stream()
                .filter(conflictedPoints -> conflictedPoints.size() > 1)
                .peek(points -> logDuplicatedPoints(validationType, points))
                .count() == 0;
    }

    private static void logDuplicatedPoints(final ValidationType validationType, List<SudokuCell> conflictedPoints) {
        var data = getDataFromNonEmptyList(conflictedPoints);
        var dataPoints = convertSudokuCellListToString(conflictedPoints);
        logger.error(DUPLICATED_DATA_POINTS, validationType, data, dataPoints);
    }

    private static int getDataFromNonEmptyList(List<SudokuCell> conflictedPoints) {
        return conflictedPoints.get(0).data();
    }

    private static String convertSudokuCellListToString(List<SudokuCell> conflictedPoints) {
        return conflictedPoints.stream().map(SudokuCell::locationAsString).collect(Collectors.joining(", "));
    }
}
