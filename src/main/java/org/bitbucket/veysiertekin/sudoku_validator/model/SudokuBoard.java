package org.bitbucket.veysiertekin.sudoku_validator.model;

import org.bitbucket.veysiertekin.sudoku_validator.utils.ArrayUtils;

import java.util.Objects;

import static org.bitbucket.veysiertekin.sudoku_validator.CommonConstants.EMPTY_FIELD;

/**
 * Sudoku Board Object
 */
public class SudokuBoard {
    private final Integer[][] board;

    public SudokuBoard(Integer[][] board) {
        this.board = board;
    }

    /**
     * Checks that board is completed or not
     *
     * @return returns whether board does not have any "null" values or not
     */
    public boolean isCompleted() {
        for (Integer[] row : board)
            for (Integer data : row)
                if (Objects.equals(data, EMPTY_FIELD))
                    return false;
        return true;
    }

    /**
     * Helper method for checking index availability for a potential value
     * Desired index must be `null`.
     *
     * @param rowIndex    `y` index of value
     * @param columnIndex `x` index of value
     * @param value       value be added
     * @return If the value suitable to be add desired index returns {@code true}, otherwise {@code false}
     */
    public boolean indexAvailable(final int rowIndex, final int columnIndex, final Integer value) {
        if (!data(rowIndex, columnIndex).contains(EMPTY_FIELD)) {
            return false;
        }

        return !boxContains(value, rowIndex, columnIndex)
                && !rowContains(value, rowIndex)
                && !columnContains(value, columnIndex);
    }

    public SudokuCell data(int row, int column) {
        return new SudokuCell(board[row][column], row, column);
    }

    public int numberOfRows() {
        return board == null ? 0 : board.length;
    }

    public Integer[] row(int rowNumber) {
        return board[rowNumber];
    }

    public void set(int rowIndex, int columnIndex, Integer value) {
        board[rowIndex][columnIndex] = value;
    }

    public SudokuBoard makeCopy() {
        return new SudokuBoard(ArrayUtils.copyWrapper(board));
    }

    /**
     * Converts board data to formatted string
     *
     * @return serialized board result
     */
    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (int row = 0; row < board.length; row++) {
            if (row != 0) {
                builder.append("\n");
                if (row % 3 == 0)
                    builder.append("-----------\n");
            }
            for (int column = 0; column < board[0].length; column++) {
                if (column > 0 && column % 3 == 0)
                    builder.append("|");
                builder.append(board[row][column]);
            }
        }
        return builder.toString();
    }

    private boolean rowContains(Integer value, Integer rowIndex) {
        return ArrayUtils.contains(board[rowIndex], value);
    }

    private boolean columnContains(Integer value, int columnIndex) {
        for (Integer[] row : board)
            if (Objects.equals(row[columnIndex], value))
                return true;
        return false;
    }

    private boolean boxContains(Integer value, Integer rowIndex, Integer columnIndex) {
        final int boxRow = calculateBoxIndex(rowIndex);
        final int boxColumn = calculateBoxIndex(columnIndex);

        for (int row = boxRow; row < boxRow + 3; row++)
            for (int column = boxColumn; column < boxColumn + 3; column++)
                if (data(row, column).contains(value))
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
