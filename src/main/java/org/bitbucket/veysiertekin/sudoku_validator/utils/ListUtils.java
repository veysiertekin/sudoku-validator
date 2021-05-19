package org.bitbucket.veysiertekin.sudoku_validator.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ListUtils {
    public static boolean checkValuesNotRepeatedExceptNulls(List<Integer> integers) {
        final List<Integer> nonNullValues = integers.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        final HashSet<Integer> nonDuplicated = new HashSet<>(nonNullValues);
        return nonNullValues.size() == nonDuplicated.size();
    }

    public static <T> List<List<T>> copy(final List<List<T>> input) {
        return input.stream()
                .map(ArrayList::new)
                .collect(toList());
    }
}
