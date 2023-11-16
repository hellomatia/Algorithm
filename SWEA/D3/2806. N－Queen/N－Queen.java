
import java.io.*;
import java.util.*;

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N;
	private static int result;
	private static boolean[][] board;
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initN();
			result = 0;
			findResult(0);
			printResult(testCase, result);
		}
		bw.flush();
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initN() throws IOException {
		N = Integer.parseInt(bf.readLine());
		board = new boolean[N][N];
	}
	
    private static void findResult(int nowRow) {
        if (nowRow == N) {
            result++;
            return;
        }
        
        for (int j = 0; j < N; j++) {
            if (hasQueen(nowRow, j)) {
                continue;
            }
            board[nowRow][j] = true;
            findResult(nowRow + 1);
            board[nowRow][j] = false;
        }
    }
    
    private static boolean hasQueen(int x, int y) {
        for (int i = 0; i < N; i++) {
            if (board[i][y]) {
                return true; // 같은 열에 이미 퀸이 있음
            }
        }

        for (int i = 0; i < N; i++) {
            if (board[x][i]) {
                return true; // 같은 행에 이미 퀸이 있음
            }
        }

        for (int i = 1; i < N; i++) {
            if (x - i >= 0 && y - i >= 0 && board[x - i][y - i]) {
                return true;
            }
            if (x + i < N && y + i < N && board[x + i][y + i]) {
                return true;
            }
            if (x - i >= 0 && y + i < N && board[x - i][y + i]) {
                return true;
            }
            if (x + i < N && y - i >= 0 && board[x + i][y - i]) {
                return true;
            }
        }

        return false;
    }
	
	private static void printResult(int testCase, int result) throws IOException {
		bw.write("#"+testCase + " " + result +"\n");
	}
}
