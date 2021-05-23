package org.bitbucket.veysiertekin.sudoku_validator.model;

import java.util.Objects;

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

    public boolean contains(Integer value) {
        return Objects.equals(data, value);
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
