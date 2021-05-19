package org.bitbucket.veysiertekin.sudoku_validator;

import org.bitbucket.veysiertekin.sudoku_validator.utils.ListUtils;
import org.bitbucket.veysiertekin.sudoku_validator.validation.BoxValidationCommand;
import org.bitbucket.veysiertekin.sudoku_validator.validation.ColumnValidationCommand;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;

public class SudokuSolver {
    private final BoxValidationCommand boxValidator = new BoxValidationCommand();
    private final ColumnValidationCommand columnValidator = new ColumnValidationCommand();

    private final List<Integer> possibleValues = IntStream.range(1, BOARD_DIMENSION + 1).boxed().collect(Collectors.toList());

    public Optional<List<List<Integer>>> solve(List<List<Integer>> input) {
        if (boardCompleted(input)) {
            return Optional.of(input);
        }
        for (int rowIndex = 0; rowIndex < BOARD_DIMENSION; rowIndex++) {
            for (int columnIndex = 0; columnIndex < BOARD_DIMENSION; columnIndex++) {
                if (input.get(rowIndex).get(columnIndex) != null)
                    continue;
                var boxRow = calculateBoxIndex(rowIndex);
                var boxColumn = calculateBoxIndex(columnIndex);
                var box = boxValidator.extractBox(input, boxRow, boxColumn);
                var row = input.get(rowIndex);
                var column = columnValidator.getWholeColumn(input, columnIndex);

                for (var value : possibleValues) {
                    if (!box.contains(value) && !row.contains(value) && !column.contains(value)) {
                        var boardCopy = ListUtils.copy(input);
                        boardCopy.get(rowIndex).set(columnIndex, value);
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

    private boolean boardCompleted(List<List<Integer>> input) {
        return input.stream()
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .count() == BOARD_DIMENSION * BOARD_DIMENSION;
    }

    private Integer calculateBoxIndex(int index) {
        return index / 3;
    }
}
