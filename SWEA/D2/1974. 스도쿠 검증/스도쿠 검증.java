import java.io.*;
import java.util.*;

public class Solution {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int SUDOKU_SIZE = 9;
    private static final int SQUARE_SIZE = 3;
    private static Set<Integer>[] rowSet;
    private static Set<Integer>[] colSet;
    private static Set<Integer>[][] squareSet;
    
    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private void solution() throws IOException {
        int testCase = Integer.parseInt(bf.readLine());
        for (int t = 1; t <= testCase; t++) {
            init();
            printResult(t, calcResult());
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        rowSet = new Set[SUDOKU_SIZE];
        colSet = new Set[SUDOKU_SIZE];
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            rowSet[i] = new HashSet<>();
            colSet[i] = new HashSet<>();
        }
        squareSet = new Set[SQUARE_SIZE][SQUARE_SIZE];
        for (int i = 0; i < SQUARE_SIZE; i++) {
            for (int j = 0; j < SQUARE_SIZE; j++) {
                squareSet[i][j] = new HashSet<>();
            }
        }
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < SUDOKU_SIZE; j++) {
                int num = Integer.parseInt(st.nextToken());
                rowSet[i].add(num);
                colSet[j].add(num);
                squareSet[i/3][j/3].add(num);

            }
        }
    }

    private int calcResult() {
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            if (rowSet[i].size() != 9 || colSet[i].size() != 9) {
                return 0;
            }
        }
        for (int i = 0; i < SQUARE_SIZE; i++) {
            for (int j = 0; j < SQUARE_SIZE; j++) {
                if (squareSet[i][j].size() != 9) {
                    return 0;
                }
            }
        }
        return 1;
    }

    private void printResult(int testCase, int result) throws IOException {
        bw.write("#"+testCase + " " + result + "\n");
    }
}
