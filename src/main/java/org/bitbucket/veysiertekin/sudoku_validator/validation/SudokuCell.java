package org.bitbucket.veysiertekin.sudoku_validator.validation;

public class SudokuCell {
    private final Integer data;
    private final int locationX;
    private final int locationY;

    public SudokuCell(final Integer data, final int locationX, final int locationY) {
        this.data = data;
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public Integer data() {
        return data;
    }

    public String locationAsString() {
        return "(" + locationX +
                ", " + locationY +
                ')';
    }

    @Override
    public String toString() {
        return "SudokuCell{" +
                "data=" + data +
                ", locationX=" + locationX +
                ", locationY=" + locationY +
                '}';
    }
}
