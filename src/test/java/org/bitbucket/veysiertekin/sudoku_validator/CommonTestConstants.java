package org.bitbucket.veysiertekin.sudoku_validator;

import java.util.Arrays;
import java.util.List;

public final class CommonTestConstants {
    /**
     * 5, 3, _ | _, 7, _ | _, _, _
     * 6, _, _ | 1, 9, 5 | _, _, _
     * _, 9, 8 | _, _, _ | _, 6, _
     * ---------------------------
     * 8, _, _ | _, 6, _ | _, _, 3
     * 4, _, _ | 8, _, 3 | _, _, 1
     * 7, _, _ | _, 2, _ | _, _, 6
     * ---------------------------
     * -, 6, _ | _, _, _ | 2, 8, _
     * -, _, _ | 4, 1, 9 | _, _, 5
     * -, _, _ | _, 8, _ | _, 7, 9
     */
    public static List<List<Integer>> VALID_BOARD = Arrays.asList(
            Arrays.asList(5, 3, null, null, 7, null, null, null, null),
            Arrays.asList(6, null, null, 1, 9, 5, null, null, null),
            Arrays.asList(null, 9, 8, null, null, null, null, 6, null),
            Arrays.asList(8, null, null, null, 6, null, null, null, 3),
            Arrays.asList(4, null, null, 8, null, 3, null, null, 1),
            Arrays.asList(7, null, null, null, 2, null, null, null, 6),
            Arrays.asList(null, 6, null, null, null, null, 2, 8, null),
            Arrays.asList(null, null, null, 4, 1, 9, null, null, 5),
            Arrays.asList(null, null, null, null, 8, null, null, 7, 9)
    );

    public static List<List<Integer>> VALID_BOARD_RESULT = Arrays.asList(
            Arrays.asList(5, 3, 4, 6, 7, 8, 9, 1, 2),
            Arrays.asList(6, 7, 2, 1, 9, 5, 3, 4, 8),
            Arrays.asList(1, 9, 8, 3, 4, 2, 5, 6, 7),
            Arrays.asList(8, 5, 9, 7, 6, 1, 4, 2, 3),
            Arrays.asList(4, 2, 6, 8, 5, 3, 7, 9, 1),
            Arrays.asList(7, 1, 3, 9, 2, 4, 8, 5, 6),
            Arrays.asList(9, 6, 1, 5, 3, 7, 2, 8, 4),
            Arrays.asList(2, 8, 7, 4, 1, 9, 6, 3, 5),
            Arrays.asList(3, 4, 5, 2, 8, 6, 1, 7, 9)
    );
}
