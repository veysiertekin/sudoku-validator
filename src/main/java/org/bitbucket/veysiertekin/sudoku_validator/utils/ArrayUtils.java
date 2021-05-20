package org.bitbucket.veysiertekin.sudoku_validator.utils;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class ArrayUtils {
    public static <T> boolean contains(final T[] array, T value) {
        return Arrays.asList(array).contains(value);
    }

    public static boolean containsDistinctValuesExceptNulls(Integer[] input) {
        return asNonNullStream(input).count()
                == asNonNullStream(input).distinct().count();
    }

    private static Stream<Integer> asNonNullStream(Integer[] input) {
        return Arrays.stream(input).filter(Objects::nonNull);
    }

    public static Integer[][] copy(Integer[][] input) {
        var result = new Integer[input.length][input[0].length];
        for (int i = 0; i < input.length; i++) {
            result[i] = Arrays.stream(input[i]).toArray(Integer[]::new);
        }
        return result;
    }
}
