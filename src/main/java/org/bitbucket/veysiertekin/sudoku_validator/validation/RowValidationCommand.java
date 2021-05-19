package org.bitbucket.veysiertekin.sudoku_validator.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RowValidationCommand implements ValidationCommand {
    @Override
    public boolean validate(List<List<Integer>> input) {
        return input.stream().allMatch(RowValidationCommand::checkValuesNotRepeated);
    }

    private static boolean checkValuesNotRepeated(List<Integer> integers) {
        final List<Integer> nonNullValues = integers.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        final HashSet<Integer> nonDuplicated = new HashSet<>(nonNullValues);
        return nonNullValues.size() == nonDuplicated.size();
    }
}
