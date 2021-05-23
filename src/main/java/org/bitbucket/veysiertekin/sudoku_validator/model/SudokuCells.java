package org.bitbucket.veysiertekin.sudoku_validator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuCells extends ArrayList<SudokuCell> {
    public SudokuCells(final List<SudokuCell> cells) {
        super(cells);
    }

    public SudokuCells(int boardDimension) {
        super(boardDimension);
    }

    @Override
    public String toString() {
        return stream().map(SudokuCell::toString).collect(Collectors.joining());
    }

    public String toLocationListString() {
        return stream().map(SudokuCell::locationAsString).collect(Collectors.joining(", "));
    }
}
