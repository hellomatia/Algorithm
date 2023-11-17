
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
	private static int T, H, W;
	private static char[][] map;
	private static char[] command;
	private static Point tank;
	private static int[] dirX = {-1, 1, 0, 0};
	private static int[] dirY = {0, 0, -1, 1};
	
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initHandW();
			initMap();
			initCommand();
			for (char oneCommand : command) {
				playCommand(oneCommand);
			}
			printResult(testCase);
		}
		bw.flush();
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initHandW() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
	}
	
	private static void initMap() throws IOException {
		map = new char[H][W];
		for (int i =0 ; i < H; i++) {
			String string = bf.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = string.charAt(j);
				if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
					tank = new Point(i, j);
				}
			}
		}
	}
	
	private static void initCommand() throws IOException {
		int num = Integer.parseInt(bf.readLine());
		command = new char[num];
		String string = bf.readLine();
		for (int i = 0; i < num; i++) {
			command[i] = string.charAt(i);
		}
	}
	
	private static void playCommand(char oneCommand) {
		if (oneCommand == 'U') {
			if (canMove(tank.x - 1, tank.y)) {
				map[tank.x][tank.y] = '.';
				tank.x -= 1;
			}
			map[tank.x][tank.y] = '^';
		}
		if (oneCommand == 'D') {
			if (canMove(tank.x + 1, tank.y)) {
				map[tank.x][tank.y] = '.';
				tank.x += 1;
			}
			map[tank.x][tank.y] = 'v';
		}
		if (oneCommand == 'L') {
			if (canMove(tank.x, tank.y - 1)) {
				map[tank.x][tank.y] = '.';
				tank.y -= 1;
			}
			map[tank.x][tank.y] = '<';
		}
		if (oneCommand == 'R') {
			if (canMove(tank.x, tank.y + 1)) {
				map[tank.x][tank.y] = '.';
				tank.y += 1;
			}
			map[tank.x][tank.y] = '>';
		}
		if (oneCommand == 'S') {
			shot();
		}
		
	}
	
	private static boolean canMove(int x, int y) {
		return 0 <= x && 0 <= y && x < H && y < W && map[x][y] == '.';
	}
	
	private static void shot() {
		int dir;
		if (map[tank.x][tank.y] == '^') {
			dir = 0;
		} else if (map[tank.x][tank.y] == 'v') {
			dir = 1;
		} else if (map[tank.x][tank.y] == '<') {
			dir = 2;
		} else {
			dir = 3;
		}
		int nextX = tank.x + dirX[dir];
		int nextY = tank.y + dirY[dir];
		while (0 <= nextX && 0 <= nextY && nextX < H && nextY < W) {
			if (map[nextX][nextY] == '*') {
				map[nextX][nextY] = '.';
				return;
			}
			if (map[nextX][nextY] == '#') {
				return;
			}
			nextX += dirX[dir];
			nextY += dirY[dir];
		}

	}
	
	private static void printResult(int testCase) throws IOException {
		bw.write("#"+testCase + " " );
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				bw.write(map[i][j]+"");
			}
			bw.write("\n");
		}
	}
}
