
import java.io.*;
import java.util.*;

class Point {
	int x;
	int y;
	int value = 0;
	int dir = 1;
	
	Point (int x, int y, int value, int dir) {
		this.x = x;
		this.y = y;
		this.value = value;
		this.dir = dir;
	}
	
	public void add() {
		if (value == 15) {
			value = 0;
			return;
		}
		value++;
	}
	
	public void sub() {
		if (value == 0) {
			value = 15;
			return;
		}
		value--;
	}
}

public class Solution {

	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, R, C;
	private static char[][] command;
	private static int[] dirX = {0, 0, -1, 1};
	private static int[] dirY = {-1, 1, 0, 0};
	private static boolean isFinished;
	private static boolean[][][][] visited;
	
	
	public static void main(String args[]) throws Exception {
		solution();
	}

	private static void solution() throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initCommand();
			isFinished = false;
			run(new Point(0, 0, 0, 1));
			printResult(testCase, isFinished);
			bw.flush();
		}
		bw.close();
	}

	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}

	private static void initCommand() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		command = new char[R][C];
		for (int i = 0; i < R; i++) {
			String string = bf.readLine();
			for (int j = 0; j < C; j++) {
				command[i][j] = string.charAt(j);
			}
		}
		visited = new boolean[R][C][16][4];
	}
	
	private static void run(Point now) {
		if (visited[now.x][now.y][now.value][now.dir] || isFinished) {
			return;
		}
		visited[now.x][now.y][now.value][now.dir] = true;

		//System.out.println(now.x + " " + now.y + " " + now.dir + " " + now.value);
		
		if (command[now.x][now.y] == '<' ) {
			now.dir = 0;
		} else if (command[now.x][now.y] == '>' ) {
			now.dir = 1;
		} else if (command[now.x][now.y] == '^' ) {
			now.dir = 2;
		} else if (command[now.x][now.y] == 'v' ) {
			now.dir = 3;
		} else if (command[now.x][now.y] == '_' ) {
			if (now.value == 0) {
				now.dir = 1;
			} else {
				now.dir = 0;
			}
		} else if (command[now.x][now.y] == '|' ) {
			if (now.value == 0) {
				now.dir = 3;
			} else {
				now.dir = 2;
			}
		} else if ('0' <= command[now.x][now.y]
				&& command[now.x][now.y] <= '9') {
			now.value = command[now.x][now.y] - '0';
		} else if (command[now.x][now.y] == '+' ) {
			now.add();
		} else if (command[now.x][now.y] == '-' ) {
			now.sub();
		} else if (command[now.x][now.y] == '@' ) {
			isFinished = true;
			return;
		} else if (command[now.x][now.y] == '?') {
			for (int i = 0; i < 4; i++) {
				int nextX = getX(now.x + dirX[i]);
				int nextY = getY(now.y + dirY[i]);
				if (visited[nextX][nextY][now.value][i] || isFinished) {
					return;
				}
				run(new Point(nextX, nextY, now.value, i));
			}
			return;
		}
		
		now.x = getX(now.x + dirX[now.dir]);
		now.y = getY(now.y + dirY[now.dir]);
		run(now);
	}
	
	private static int getX(int x) {
		if (x < 0) {
			return R - 1;
		}
		if (x == R) {
			return 0;
		}
		return x;
	}
	
	private static int getY(int y) {
		if (y < 0) {
			return C - 1;
		}
		if (y == C) {
			return 0;
		}
		return y;
	}
	
	

	private static void printResult(int testCase, boolean result) throws IOException {
		bw.write("#" + testCase + " " );
		if (result) {
			bw.write("YES");
		} else {
			bw.write("NO");
		}
		bw.write("\n");
	}
}
