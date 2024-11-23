import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
                
            int n = Integer.parseInt(br.readLine());
            char[][] board = init(br, n);

            int[] heart = findHeart(board);
            int[] lengths = findLengths(board, heart);

            bw.write((heart[0] + 1) + " " + (heart[1] + 1) + "\n");
            for (int length : lengths) {
                bw.write(length + " ");
            }
            bw.flush();
        }
    }

    private char[][] init(BufferedReader br, int n) throws IOException {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        return board;
    }

    private int[] findHeart(char[][] board) {
        int n = board.length;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (isHeart(board, i, j)) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private boolean isHeart(char[][] board, int x, int y) {
        return board[x][y] == '*' && 
               board[x-1][y] == '*' && 
               board[x+1][y] == '*' && 
               board[x][y-1] == '*' && 
               board[x][y+1] == '*';
    }

    private int[] findLengths(char[][] board, int[] heart) {
        int[] lengths = new int[5]; // leftArm, rightArm, waist, leftLeg, rightLeg
        int x = heart[0], y = heart[1];

        // 왼팔
        for (int j = y - 1; j >= 0 && board[x][j] == '*'; j--) {
            lengths[0]++;
        }

        // 오른팔
        for (int j = y + 1; j < board.length && board[x][j] == '*'; j++) {
            lengths[1]++;
        }

        // 허리
        int waistEnd = x;
        for (int i = x + 1; i < board.length && board[i][y] == '*'; i++) {
            lengths[2]++;
            waistEnd = i;
        }

        // 왼쪽 다리
        for (int i = waistEnd + 1; i < board.length && board[i][y-1] == '*'; i++) {
            lengths[3]++;
        }

        // 오른쪽 다리
        for (int i = waistEnd + 1; i < board.length && board[i][y+1] == '*'; i++) {
            lengths[4]++;
        }

        return lengths;
    }
}