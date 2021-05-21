package org.bitbucket.veysiertekin.sudoku_validator.validation;

public interface ValidationCommand {
    boolean validate(final Integer[][] input);
}
