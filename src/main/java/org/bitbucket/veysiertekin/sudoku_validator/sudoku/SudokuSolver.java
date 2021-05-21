package org.bitbucket.veysiertekin.sudoku_validator.sudoku;

import org.bitbucket.veysiertekin.sudoku_validator.ApplicationMessage;
import org.bitbucket.veysiertekin.sudoku_validator.utils.ArrayUtils;
import org.bitbucket.veysiertekin.sudoku_validator.utils.Logger;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;
import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.EMPTY_FIELD;

public class SudokuSolver {
    private static final Logger logger = Logger.getInstance();
    private final SudokuValidator sudokuValidator = new SudokuValidator();

    private final List<Integer> possibleValues = IntStream.range(1, BOARD_DIMENSION + 1)
            .boxed().collect(Collectors.toList());

    public Optional<Integer[][]> solve(final Integer[][] input) {
        if (!sudokuValidator.isBoardValid(input)) {
            return Optional.empty();
        }
        // Prevent side-effects by copying wrapper arrays
        final var copy = ArrayUtils.copyWrapper(input);
        final var result = solveValidInput(copy);
        if (result.isEmpty()) {
            logger.error(ApplicationMessage.UNSOLVABLE_PUZZLE);
        }
        return result;
    }

    private Optional<Integer[][]> solveValidInput(final Integer[][] input) {
        if (boardCompleted(input)) {
            return Optional.of(input);
        }
        for (int rowIndex = 0; rowIndex < BOARD_DIMENSION; rowIndex++) {
            for (int columnIndex = 0; columnIndex < BOARD_DIMENSION; columnIndex++) {
                if (!Objects.equals(input[rowIndex][columnIndex], EMPTY_FIELD))
                    continue;

                for (var value : possibleValues) {
                    if (canValueBeInsertedToEmptyIndex(input, value, rowIndex, columnIndex)) {
                        input[rowIndex][columnIndex] = value;
                        var result = solveValidInput(input);
                        if (result.isPresent()) {
                            return result;
                        }
                        input[rowIndex][columnIndex] = EMPTY_FIELD;
                    }
                }
                // If an index is empty, but non of the possible
                // values match, we can skip these branches
                return Optional.empty();
            }
        }
        /*
         * It is impossible to get here because of the early exits in the function
         */
        return Optional.empty();
    }

    private boolean boardCompleted(final Integer[][] input) {
        for (Integer[] row : input)
            for (Integer data : row)
                if (Objects.equals(data, EMPTY_FIELD))
                    return false;
        return true;
    }

    public boolean canValueBeInsertedToEmptyIndex(final Integer[][] input, final Integer value,
                                                  final int rowIndex, final int columnIndex) {
        var boxRow = calculateBoxIndex(rowIndex);
        var boxColumn = calculateBoxIndex(columnIndex);

        return !boxContains(input, value, boxRow, boxColumn)
                && !rowContains(input, value, rowIndex)
                && !columnContains(input, value, columnIndex);
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
        for (int row = rowIndex * 3; row < rowIndex * 3 + 3; row++)
            for (int column = columnIndex * 3; column < columnIndex * 3 + 3; column++)
                if (Objects.equals(input[row][column], value))
                    return true;
        return false;
    }

    private Integer calculateBoxIndex(int index) {
        return index / 3;
    }
}