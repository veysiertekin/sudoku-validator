package org.bitbucket.veysiertekin.sudoku_validator.validation;

import java.util.List;

public interface ValidationCommand {
    boolean validate(final List<List<Integer>> input);
}
