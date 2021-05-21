package org.bitbucket.veysiertekin.sudoku_validator.utils;

import java.util.Arrays;

public class ArrayUtils {

    public static <T> boolean contains(final T[] array, T value) {
        return Arrays.asList(array).contains(value);
    }

    public static Integer[][] copyWrapper(Integer[][] input) {
        var result = new Integer[input.length][input[0].length];
        for (int i = 0; i < input.length; i++) {
            result[i] = copyWrapper(input[i]);
        }
        return result;
    }

    private static Integer[] copyWrapper(Integer[] array) {
        return Arrays.stream(array).toArray(Integer[]::new);
    }
}
