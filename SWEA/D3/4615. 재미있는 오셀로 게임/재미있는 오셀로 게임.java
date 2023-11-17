
import java.io.*;
import java.util.*;

class Point {
	int x;
	int y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N, M;
	private static int[][] map;
	private static int[] dirX = {0, 0, -1, 1, 1, 1, -1, -1};
	private static int[] dirY = {1, -1, 0, 0, 1, -1, -1, 1};
	
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initNandM();
			initMap();
			for (int i = 0; i < M; i++) {
				readCommand();
			}
			printResult(testCase);
		}
		bw.flush();
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initNandM() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	}
	
	private static void initMap() {
		map = new int[N][N];
		
		int midX = N / 2 - 1;
		int midY = midX;
		
		map[midX][midY] = 2;
		map[midX + 1][midY + 1] = 2;
		map[midX][midY + 1] = 1;
		map[midX + 1][midY] = 1;
	}
	
	private static void readCommand() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int y = Integer.parseInt(st.nextToken()) - 1;
		int x = Integer.parseInt(st.nextToken()) - 1;
		int color = Integer.parseInt(st.nextToken());
		
		play(x, y, color);
	}
	
	private static void play(int x, int y, int color) {
		map[x][y] = color;
		Queue<Point> queue = new LinkedList<>();

		for (int i = 0; i < 8; i++) {
			queue.clear();
			int nextX = x + dirX[i];
			int nextY = y+ dirY[i];
			while (0 <= nextX && 0 <= nextY && nextX < N &&  nextY< N) {
				if (map[nextX][nextY] == 0) {
					break;
				}
				if (map[nextX][nextY] == color) {
					while (!queue.isEmpty()) {
						Point change = queue.poll();
						map[change.x][change.y] = color;
					}
					break;
				}
				queue.offer(new Point(nextX, nextY));
				nextX += dirX[i];
				nextY += dirY[i];
			}
		}
		
	}
		
	private static void printResult(int testCase) throws IOException {
		int white = 0;
		int black = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2) {
					white++;
				} else if (map[i][j] == 1) {
					black++;
				}
			}
		}
		
		bw.write("#"+testCase + " " + black + " " + white);
		bw.write("\n");
	}
}
