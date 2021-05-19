package org.bitbucket.veysiertekin.sudoku_validator;

import org.bitbucket.veysiertekin.sudoku_validator.utils.ArrayUtils;
import org.bitbucket.veysiertekin.sudoku_validator.validation.BoxValidationCommand;
import org.bitbucket.veysiertekin.sudoku_validator.validation.ColumnValidationCommand;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;

public class SudokuSolver {
    private final BoxValidationCommand boxValidator = new BoxValidationCommand();
    private final ColumnValidationCommand columnValidator = new ColumnValidationCommand();

    private final List<Integer> possibleValues = IntStream.range(1, BOARD_DIMENSION + 1).boxed().collect(Collectors.toList());

    public Optional<Integer[][]> solve(final Integer[][] input) {
        if (boardCompleted(input)) {
            return Optional.of(input);
        }
        for (int rowIndex = 0; rowIndex < BOARD_DIMENSION; rowIndex++) {
            for (int columnIndex = 0; columnIndex < BOARD_DIMENSION; columnIndex++) {
                if (input[rowIndex][columnIndex] != null)
                    continue;
                var boxRow = calculateBoxIndex(rowIndex);
                var boxColumn = calculateBoxIndex(columnIndex);
                var box = boxValidator.extractBox(input, boxRow, boxColumn);
                var row = input[rowIndex];
                var column = columnValidator.getWholeColumn(input, columnIndex);

                for (var value : possibleValues) {
                    if (!ArrayUtils.contains(box, value) && !ArrayUtils.contains(row, value) && !ArrayUtils.contains(column, value)) {
                        var boardCopy = ArrayUtils.copy(input);
                        boardCopy[rowIndex][columnIndex] = value;
                        var result = solve(boardCopy);
                        if (result.isPresent()) {
                            return result;
                        }
                    }
                }
            }
        }
        return Optional.empty();
    }

    private boolean boardCompleted(Integer[][] input) {
        for (Integer[] integers : input)
            for (Integer integer : integers)
                if (integer == null)
                    return false;
        return true;
    }

    private Integer calculateBoxIndex(int index) {
        return index / 3;
    }
}
