import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Point {

    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static final int SUDOKU_MAX_LEN = 9;
    static final int SUDOKU_MIN_LEN = 3;
    static final int SUDOKU_MIN_NUMBER = 1;
    static final int SUDOKU_MAX_NUMBER = 9;
    static final int SUDOKU_BLANK = 0;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[][] sudokuBoard = new int[SUDOKU_MAX_LEN][SUDOKU_MAX_LEN];
    static List<Point> blanks = new ArrayList<>();
    static HashSet<Integer>[] rowHashSet = new HashSet[SUDOKU_MAX_LEN];
    static HashSet<Integer>[] colHashSet = new HashSet[SUDOKU_MAX_LEN];
    static HashSet<Integer>[][] sudokuBlockHashSet = new HashSet[SUDOKU_MIN_LEN][SUDOKU_MIN_LEN];

    static boolean finished = false;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        playSudokuGame(0);

        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        initHash();
        initBoard();
    }

    private void initHash() {
        for (int row = 0; row < SUDOKU_MAX_LEN; row++) {
            rowHashSet[row] = new HashSet<>();
        }
        for (int col = 0; col < SUDOKU_MAX_LEN; col++) {
            colHashSet[col] = new HashSet<>();
        }
        for (int row = 0; row < SUDOKU_MIN_LEN; row++) {
            for (int col = 0; col < SUDOKU_MIN_LEN; col++) {
                sudokuBlockHashSet[row][col] = new HashSet<>();
            }
        }
    }

    private void initBoard() throws IOException {
        for (int row = 0; row < SUDOKU_MAX_LEN; row++) {
            String rowValues = bf.readLine();
            for (int col = 0; col < SUDOKU_MAX_LEN; col++) {
                sudokuBoard[row][col] = rowValues.charAt(col) - '0';
                if (sudokuBoard[row][col] == SUDOKU_BLANK) {
                    blanks.add(new Point(row, col));
                    continue;
                }
                addHashSet(row, col, sudokuBoard[row][col]);
            }
        }
    }

    private void addHashSet(int row, int col, int value) {
        addRowHashSet(row, value);
        addColHashSet(col, value);
        addBlockHashSet(row, col, value);
    }

    private void addRowHashSet(int row, int value) {
        rowHashSet[row].add(value);
    }

    private void addColHashSet(int col, int value) {
        colHashSet[col].add(value);
    }

    private void addBlockHashSet(int row, int col, int value) {
        row = getBlockRow(row);
        col = getBlockCol(col);
        sudokuBlockHashSet[row][col].add(value);
    }

    private int getBlockRow(int row) {
        if (row < SUDOKU_MIN_LEN) {
            return 0;
        }
        if (row < 2 * SUDOKU_MIN_LEN) {
            return 1;
        }
        return 2;
    }

    private int getBlockCol(int col) {
        if (col < SUDOKU_MIN_LEN) {
            return 0;
        }
        if (col < 2 * SUDOKU_MIN_LEN) {
            return 1;
        }
        return 2;
    }

    private void playSudokuGame(int index) {
        if (finished) {
            return;
        }

        if (index == blanks.size()) {
            finished = true;
            printResult();
            return;
        }

        Point now = blanks.get(index);

        for (int value = SUDOKU_MIN_NUMBER; value <= SUDOKU_MAX_NUMBER; value++) {
            if (canInsert(now.x, now.y, value)) {
                addHashSet(now.x, now.y, value);
                sudokuBoard[now.x][now.y] = value;
                playSudokuGame(index + 1);
                removeHashSet(now.x, now.y, value);
                sudokuBoard[now.x][now.y] = SUDOKU_BLANK;
            }
        }
    }

    private boolean canInsert(int row, int col, int value) {
        return canRowInsert(row, value) && canColInsert(col, value) && canBlockInsert(row, col,
                value);
    }

    private boolean canRowInsert(int row, int value) {
        return !rowHashSet[row].contains(value);
    }

    private boolean canColInsert(int col, int value) {
        return !colHashSet[col].contains(value);
    }

    private boolean canBlockInsert(int row, int col, int value) {
        row = getBlockRow(row);
        col = getBlockCol(col);
        return !sudokuBlockHashSet[row][col].contains(value);
    }

    private void removeHashSet(int row, int col, int value) {
        removeRowHashSet(row, value);
        removeColHashSet(col, value);
        removeBlockHashSet(row, col, value);
    }

    private void removeRowHashSet(int row, int value) {
        rowHashSet[row].remove(value);
    }

    private void removeColHashSet(int col, int value) {
        colHashSet[col].remove(value);
    }

    private void removeBlockHashSet(int row, int col, int value) {
        row = getBlockRow(row);
        col = getBlockCol(col);
        sudokuBlockHashSet[row][col].remove(value);
    }

    private void printResult() {
        for (int row = 0; row < SUDOKU_MAX_LEN; row++) {
            for (int col = 0; col < SUDOKU_MAX_LEN; col++) {
                System.out.print(sudokuBoard[row][col]);
            }
            System.out.println();
        }
    }
}