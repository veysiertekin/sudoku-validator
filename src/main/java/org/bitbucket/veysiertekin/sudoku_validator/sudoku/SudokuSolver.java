package org.bitbucket.veysiertekin.sudoku_validator.sudoku;

import org.bitbucket.veysiertekin.sudoku_validator.ApplicationMessage;
import org.bitbucket.veysiertekin.sudoku_validator.model.SudokuBoard;
import org.bitbucket.veysiertekin.sudoku_validator.utils.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.BOARD_DIMENSION;
import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.EMPTY_FIELD;

/**
 * Finds a possible solution of given sudoku board.
 */
public class SudokuSolver {
    private static final Logger logger = Logger.getInstance();

    private SudokuSolver() {
    }

    private static class Holder {
        /**
         * Lazy singleton instance
         */
        private static final SudokuSolver INSTANCE = new SudokuSolver();
    }

    public static SudokuSolver getInstance() {
        return SudokuSolver.Holder.INSTANCE;
    }

    private final SudokuValidator sudokuValidator = SudokuValidator.getInstance();

    private final List<Integer> possibleValues = IntStream.range(1, BOARD_DIMENSION + 1)
            .boxed().collect(Collectors.toList());

    /**
     * <p>
     * Performs deep-first-search algorithm to find a possible solution.
     * <p>
     * Prints solution to {@link System#out} if anything has been found.
     *
     * @param input given input
     * @return returns a possible solution if anything has been found, otherwise returns {@link Optional#empty()}
     */
    public Optional<SudokuBoard> solve(final SudokuBoard input) {
        if (!sudokuValidator.isBoardValid(input)) {
            return Optional.empty();
        }
        // Prevent side-effects by copying wrapper objects
        final var copy = input.makeCopy();
        final var result = solveValidInput(copy);
        if (result.isEmpty()) {
            logger.error(ApplicationMessage.UNSOLVABLE_PUZZLE);
        } else {
            logger.info(ApplicationMessage.SOLUTION_HAS_BEEN_FOUND, result.get().toString());
        }
        return result;
    }

    private Optional<SudokuBoard> solveValidInput(final SudokuBoard input) {
        if (input.isCompleted()) {
            return Optional.of(input);
        }
        for (int rowIndex = 0; rowIndex < BOARD_DIMENSION; rowIndex++) {
            for (int columnIndex = 0; columnIndex < BOARD_DIMENSION; columnIndex++) {
                if (!input.data(rowIndex, columnIndex).contains(EMPTY_FIELD))
                    continue;

                for (var value : possibleValues) {
                    if (input.indexAvailable(rowIndex, columnIndex, value)) {
                        input.set(rowIndex, columnIndex, value);
                        var result = solveValidInput(input);
                        if (result.isPresent()) {
                            return result;
                        }
                        input.set(rowIndex, columnIndex, EMPTY_FIELD);
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
}
