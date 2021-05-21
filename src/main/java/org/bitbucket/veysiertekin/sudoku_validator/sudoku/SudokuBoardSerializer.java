package org.bitbucket.veysiertekin.sudoku_validator.sudoku;

public class SudokuBoardSerializer {
    /**
     * Converts given board data to String
     *
     * @param board sudoku board
     * @return serialized board result
     */
    public String serializeAsString(final Integer[][] board) {
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
}
