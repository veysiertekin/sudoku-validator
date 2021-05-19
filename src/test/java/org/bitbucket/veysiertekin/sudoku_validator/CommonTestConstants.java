package org.bitbucket.veysiertekin.sudoku_validator;

import java.util.Arrays;
import java.util.List;

public final class CommonTestConstants {
    public static List<List<Integer>> VALID_BOARD = Arrays.asList(
            Arrays.asList(   5,    3, null, null,    7, null, null, null, null),
            Arrays.asList(   6, null, null,    1,    9,    5, null, null, null),
            Arrays.asList(null,    9,    8, null, null, null, null,    6, null),
            Arrays.asList(   8, null, null, null,    6, null, null, null,    3),
            Arrays.asList(   4, null, null,    8, null,    3, null, null,    1),
            Arrays.asList(   7, null, null, null,    2, null, null, null,    6),
            Arrays.asList(null,    6, null, null, null, null,    2,    8, null),
            Arrays.asList(null, null, null,    4,    1,    9, null, null,    5),
            Arrays.asList(null, null, null, null,    8, null, null,    7,    9)
    );
}
