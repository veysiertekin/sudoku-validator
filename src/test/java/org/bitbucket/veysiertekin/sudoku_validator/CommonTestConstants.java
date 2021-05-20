package org.bitbucket.veysiertekin.sudoku_validator;

public final class CommonTestConstants {
    /**
     * <pre>
     * 5, 3, _ | _, 7, _ | _, _, _
     * 6, _, _ | 1, 9, 5 | _, _, _
     * _, 9, 8 | _, _, _ | _, 6, _
     * ---------------------------
     * 8, _, _ | _, 6, _ | _, _, 3
     * 4, _, _ | 8, _, 3 | _, _, 1
     * 7, _, _ | _, 2, _ | _, _, 6
     * ---------------------------
     * _, 6, _ | _, _, _ | 2, 8, _
     * _, _, _ | 4, 1, 9 | _, _, 5
     * _, _, _ | _, 8, _ | _, 7, 9
     * </pre>
     */
    public final static Integer[][] VALID_BOARD_SAMPLE = new Integer[][]{
            new Integer[]{5, 3, null, null, 7, null, null, null, null},
            new Integer[]{6, null, null, 1, 9, 5, null, null, null},
            new Integer[]{null, 9, 8, null, null, null, null, 6, null},
            new Integer[]{8, null, null, null, 6, null, null, null, 3},
            new Integer[]{4, null, null, 8, null, 3, null, null, 1},
            new Integer[]{7, null, null, null, 2, null, null, null, 6},
            new Integer[]{null, 6, null, null, null, null, 2, 8, null},
            new Integer[]{null, null, null, 4, 1, 9, null, null, 5},
            new Integer[]{null, null, null, null, 8, null, null, 7, 9}
    };

    public final static Integer[][] VALID_BOARD_RESULT = new Integer[][]{
            new Integer[]{5, 3, 4, 6, 7, 8, 9, 1, 2},
            new Integer[]{6, 7, 2, 1, 9, 5, 3, 4, 8},
            new Integer[]{1, 9, 8, 3, 4, 2, 5, 6, 7},
            new Integer[]{8, 5, 9, 7, 6, 1, 4, 2, 3},
            new Integer[]{4, 2, 6, 8, 5, 3, 7, 9, 1},
            new Integer[]{7, 1, 3, 9, 2, 4, 8, 5, 6},
            new Integer[]{9, 6, 1, 5, 3, 7, 2, 8, 4},
            new Integer[]{2, 8, 7, 4, 1, 9, 6, 3, 5},
            new Integer[]{3, 4, 5, 2, 8, 6, 1, 7, 9}
    };
}
