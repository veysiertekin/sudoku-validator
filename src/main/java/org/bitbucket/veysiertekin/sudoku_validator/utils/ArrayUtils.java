package org.bitbucket.veysiertekin.sudoku_validator.utils;

import java.util.Arrays;

/**
 * Contains common array processing methods
 */
public class ArrayUtils {

    /**
     * Checks whether an array contains given element
     *
     * @param array given array
     * @param value value to be checked
     * @param <T>   works on any type array
     * @return {@code true} if the array contains given value, otherwise {@code false}
     */
    public static <T> boolean contains(final T[] array, T value) {
        return Arrays.asList(array).contains(value);
    }

    /**
     * Copies only wrapper array objects to prevent side-effects could be by object reference changes
     *
     * @param input array to be copied
     * @return copy wrapper array of given input
     */
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
