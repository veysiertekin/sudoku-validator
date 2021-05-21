package org.bitbucket.veysiertekin.sudoku_validator.validation;

import org.bitbucket.veysiertekin.sudoku_validator.Logger;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.bitbucket.veysiertekin.sudoku_validator.ApplicationMessage.DUPLICATED_DATA_POINTS;

public class ValidationHelper {
    private final static Logger logger = Logger.getInstance();

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
