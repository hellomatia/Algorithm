
import java.io.*;
import java.util.*;

public class Main {
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int N, L;
	private static int[][] map;
	private static int[] dirX = {-1, 1, 0 ,0};
	private static int[] dirY = {0, 0, -1, 1};
	private static boolean[] rowCan;
	private static boolean[] colCan;
	private static boolean[][] visited;
	
	
	public static void main(String[] args) throws IOException {
		init();
		findCanMove();
		printResult();
		bw.flush();
		bw.close();
	}
	
	private static void init() throws IOException {
		initNandL();
		initMap();
	}
	
	private static void initNandL() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
	}
	
	private static void initMap() throws IOException {
		map = new int[N][N];
		rowCan = new boolean[N];
		colCan = new boolean[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private static void findCanMove() {
		visited = new boolean[N][N];
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (col == N - 1) {
					rowCan[row] = true;
				}
				if (!canMove(row, col, 3)) {
					break;
				}
			}
		}
		
		visited = new boolean[N][N];
		for (int col = 0; col < N; col++) {
			for (int row = 0; row < N; row++) {
				if (row == N - 1) {
					colCan[col] = true;
				}
				if (!canMove(row, col, 1)) {
					break;
				}
			}
		}
	}
	
	
	
	private static boolean canMove(int x, int y, int dir) {
		int nextX = x + dirX[dir];
		int nextY = y + dirY[dir];
		
		if (nextX < 0 || nextY < 0 || N <= nextX || N <= nextY) {
			return false;
		}

		if (map[nextX][nextY] == map[x][y] - 1 && canDownPlace(x, y, dir)) {
			return true;
		}
		if (map[nextX][nextY] - 1 == map[x][y] && canUpPlace(nextX, nextY, dir)) {
			return true;
		}
		
		
		return map[nextX][nextY] == map[x][y];
	}
	
	private static boolean canDownPlace(int x, int y, int dir) {
		int nextX = x;
		int nextY = y;
		int hight = map[x][y];
		for (int i = 0; i < L; i++) {
			nextX += dirX[dir];
			nextY += dirY[dir];
			if (nextX < 0 || nextY < 0 || N <= nextX || N <= nextY 
					|| map[nextX][nextY] != hight - 1 || visited[nextX][nextY]) {
				return false;
			}
		}	
		
		dir = changeDir(dir);
		for (int i = 0; i < L; i++) {
			visited[nextX][nextY] = true;
			nextX += dirX[dir];
			nextY += dirY[dir];
		}
		return true;
	}
	
	private static boolean canUpPlace(int x, int y, int dir) {
		dir = changeDir(dir);
		int nextX = x;
		int nextY = y;
		int hight = map[x][y];
		for (int i = 0; i < L; i++) {
			nextX += dirX[dir];
			nextY += dirY[dir];
			if (nextX < 0 || nextY < 0 || N <= nextX || N <= nextY 
					|| map[nextX][nextY] + 1 != hight || visited[nextX][nextY]) {
				return false;
			}
		}	
		
		dir = changeDir(dir);
		for (int i = 0; i < L; i++) {
			visited[nextX][nextY] = true;
			nextX += dirX[dir];
			nextY += dirY[dir];
		}
		return true;
	}
	
	private static int changeDir(int dir) {
		if (dir == 0) {
			return 1;
		} else if (dir == 1) {
			return 0;
		}  else if (dir == 2) {
			return 3;
		} 
		return 2;
	}
	
	private static void printResult() throws IOException {
		int count = 0;
		for (int i = 0; i < N; i++) {
			if (rowCan[i]) {
				count++;
			}
		}
		for (int i = 0; i < N; i++) {
			if (colCan[i]) {
				count++;
			}
		}
		bw.write(count + "\n");
	}

}
