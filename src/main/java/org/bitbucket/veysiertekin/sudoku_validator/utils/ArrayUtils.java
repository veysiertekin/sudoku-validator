package org.bitbucket.veysiertekin.sudoku_validator.utils;

import java.util.Arrays;
import java.util.Objects;

public class ArrayUtils {
    public static <T> boolean contains(final T[] array, T value) {
        for (var result : array)
            if (Objects.equals(result, value))
                return true;
        return false;
    }

    public static boolean checkValuesNotRepeatedExceptNulls(Integer[] integers) {
        return Arrays.stream(integers).filter(Objects::nonNull).count()
                == Arrays.stream(integers).filter(Objects::nonNull).distinct().count();
    }
}
