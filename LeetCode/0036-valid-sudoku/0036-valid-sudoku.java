// Valid Sudoku

// Each row must contains the digits 1 - 9
// Each column must contain the digits 1 - 9
// Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1 - 9

// My Solution
// 1. Use Set!!! -> Set can store only unique item
// 2. Make Set 3 type
//   2-1. Each row
//   2-2. Each column
//   2-3. Each of the nine sub-box

import java.util.*;

class Solution {
    private Set<Integer>[] rowSets;
    private Set<Integer>[] columnSets;
    private Set<Integer>[] subBoxesSets;

    public boolean isValidSudoku(char[][] board) {
        init();
        return validRow(board) && validColumn(board) && validSubBox(board);
    }

    private void init() {
        rowSets = new Set[9];
        columnSets = new Set[9];
        subBoxesSets = new Set[9];

        for (int i = 0; i < 9; i++) {
            rowSets[i] = new HashSet<>();
            columnSets[i] = new HashSet<>();
            subBoxesSets[i] = new HashSet<>();
        }
    }

    private boolean validRow(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int number = board[i][j] - '0';
                if (!validNumber(number)) {
                    continue;
                }
                if (rowSets[i].contains(number)) {
                    return false;
                }
                rowSets[i].add(number);
            }
        }
        return true;
    }

    private boolean validColumn(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int number = board[j][i] - '0';
                if (!validNumber(number)) {
                    continue;
                }
                if (columnSets[i].contains(number)) {
                    return false;
                }
                columnSets[i].add(number);
            }
        }
        return true;
    }

    private boolean validSubBox(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int number = board[i][j] - '0';
                int boxNumber = calcSubBoxNumber(i, j);
                if (!validNumber(number)) {
                    continue;
                }
                if (subBoxesSets[boxNumber].contains(number)) {
                    return false;
                }
                subBoxesSets[boxNumber].add(number);
            }
        }
        return true;
    }

    private boolean validNumber(int number) {
        return 0 < number && number <= 9;
    }

    private int calcSubBoxNumber(int x, int y) {
        if (x < 3) {
            if (y < 3) {
                return 0;
            } else if (y < 6) {
                return 1;
            } else {
                return 2;
            }
        } else if (x < 6) {
            if (y < 3) {
                return 3;
            } else if (y < 6) {
                return 4;
            } else {
                return 5;
            }
        } else {
            if (y < 3) {
                return 6;
            } else if (y < 6) {
                return 7;
            } else {
                return 8;
            }
        }
    }
}