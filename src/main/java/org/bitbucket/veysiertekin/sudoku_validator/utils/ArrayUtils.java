package org.bitbucket.veysiertekin.sudoku_validator.utils;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class ArrayUtils {
    public static <T> boolean contains(final T[] array, T value) {
        return Arrays.asList(array).contains(value);
    }

    public static boolean containsDistinctValuesExceptNulls(Integer[] integers) {
        return asNonNullStream(integers).count()
                == asNonNullStream(integers).distinct().count();
    }

    private static Stream<Integer> asNonNullStream(Integer[] integers) {
        return Arrays.stream(integers).filter(Objects::nonNull);
    }
}
