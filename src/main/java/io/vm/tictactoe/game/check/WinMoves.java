package io.vm.tictactoe.game.check;

class WinMoves {
    private WinMoves(){}

    // TODO: extract a function to generate the win moves in order to support n>4
    static final int[][] THREE_CELLS_WIN_MOVES = {
            //Rows on a single layer
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {9, 10, 11}, {12, 13, 14}, {15, 16, 17}, {18, 19, 20},
            {21, 22, 23}, {24, 25, 26},

            //Columns on a single layer
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {9, 12, 15}, {10, 13, 16}, {11, 14, 17}, {18, 21, 24},
            {19, 22, 25}, {20, 23, 26},

            //Diagonals on a single layer
            {0, 4, 8}, {2, 4, 6}, {9, 13, 17}, {11, 13, 15},
            {18, 22, 26}, {20, 22, 24},

            //Down through layers
            {0, 9, 18}, {1, 10, 19}, {2, 11, 20}, {3, 12, 21}, {4, 13, 22}, {5, 14, 23}, {6, 15, 24},
            {7, 16, 25}, {8, 17, 26},

            //Diagonals across layers
            {0, 12, 24}, {1, 13, 25}, {2, 14, 26}, {6, 12, 18}, {7, 13, 19}, {8, 14, 20}, {0, 10, 20},
            {3, 13, 23}, {6, 16, 26}, {2, 10, 18}, {5, 13, 21}, {8, 16, 24}, {0, 13, 26}, {2, 13, 24},
            {6, 13, 20}, {8, 13, 18},
    };

    static final int[][] FOUR_CELLS_WIN_MOVES = {
            //Rows on a single layer
            {0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15}, {16, 17, 18, 19}, {20, 21, 22, 23}, {24, 25, 26, 27},
            {28, 29, 30, 31}, {32, 33, 34, 35}, {36, 37, 38, 39}, {40, 41, 42, 43}, {44, 45, 46, 47}, {48, 49, 50, 51}, {52, 53, 54, 55},
            {56, 57, 58, 59}, {60, 61, 62, 63},

            //Columns on a single layer
            {0, 4, 8, 12}, {1, 5, 9, 13}, {2, 6, 10, 14}, {2, 6, 10, 14}, {3, 7, 11, 15}, {16, 20, 24, 28}, {17, 21, 25, 29},
            {18, 22, 26, 30}, {19, 23, 27, 31}, {32, 36, 40, 44}, {33, 37, 41, 45}, {34, 38, 42, 46}, {35, 39, 43, 47},
            {48, 52, 56, 60}, {49, 53, 57, 61}, {50, 54, 58, 62}, {51, 55, 59, 63},

            //Diagonals on a single layer
            {0, 5, 10, 15}, {3, 6, 9, 12}, {16, 21, 26, 31}, {19, 22, 25, 28}, {32, 37, 42, 47}, {35, 38, 41, 44},
            {48, 53, 58, 63}, {51, 54, 57, 60},

            //Down through layers
            {0, 16, 32, 48}, {1, 17, 33, 49}, {2, 18, 34, 50}, {3, 19, 35, 51}, {4, 20, 36, 52}, {5, 21, 37, 53}, {6, 22, 38, 54},
            {7, 23, 39, 55}, {8, 24, 40, 56}, {9, 25, 41, 57}, {10, 26, 42, 58}, {11, 27, 43, 59}, {12, 28, 44, 60}, {13, 29, 45, 61},
            {14, 30, 46, 62}, {15, 31, 47, 63},

            //Diagonals across layers
            {0, 20, 40, 60}, {1, 21, 41, 61}, {2, 22, 42, 62}, {3, 23, 43, 63}, {12, 24, 36, 48}, {13, 25, 37, 49},
            {14, 26, 38, 50}, {15, 27, 39, 51}, {0, 17, 34, 51}, {4, 21, 38, 55}, {8, 25, 42, 59}, {12, 29, 46, 63},
            {3, 18, 33, 48}, {7, 22, 37, 52}, {11, 26, 41, 56}, {15, 30, 45, 60}, {0, 21, 42, 63}, {3, 22, 41, 60},
            {12, 25, 38, 51}, {15, 26, 37, 48},
    };
}