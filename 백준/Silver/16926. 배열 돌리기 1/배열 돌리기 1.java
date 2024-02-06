import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private int N, M, R;
	private int[][] map;
	private boolean[][] prevVisited;
	private boolean[][] visited;
	
	private final int[] dirX = {1, 0, -1, 0};
	private final int[] dirY = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
	
	private void solution() throws IOException {
		init();
		calcResult();
		printResult();
		bw.flush();
		bw.close();
	}
	
	private void init() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		prevVisited = new boolean[N][M];
		visited = new boolean[N][M];
	}
	
	private void calcResult() throws IOException {
		int x = 0;
		int y = 0;
		while (true) {
			if (visited[x][y]) {
				break;
			}
			rotateMap(x, y);
			x += 1;
			y += 1;
		}
	}
	
	private void rotateMap(int x, int y) {
		Queue<Integer> queue = new ArrayDeque<>();
		int d = 0;
		int nowX = x;
		int nowY = y;
		while (true) {
			if (!isIn(nowX, nowY) || visited[nowX][nowY]) {
				nowX -= dirX[d];
				nowY -= dirY[d];
				d++;
				if (d == 4) {
					break;
				}
				nowX += dirX[d];
				nowY += dirY[d];
				if (visited[nowX][nowY]) {
					break;
				}
			}
			visited[nowX][nowY] = true;
			queue.offer(map[nowX][nowY]);
			nowX += dirX[d];
			nowY += dirY[d];
		}

		int rotateSize = queue.size() - R;
		while (rotateSize < 0) {
			rotateSize += queue.size();
		}
		for (int i = 0; i < rotateSize; i++) {
			queue.offer(queue.poll());
		}
		
		d = 0;
		nowX = x;
		nowY = y;
		while (!queue.isEmpty()) {
			if (!isIn(nowX, nowY) || prevVisited[nowX][nowY]) {
				nowX -= dirX[d];
				nowY -= dirY[d];
				d++;
				if (d == 4) {
					break;
				}
				nowX += dirX[d];
				nowY += dirY[d];
				if (prevVisited[nowX][nowY]) {
					break;
				}
			}
			prevVisited[nowX][nowY] = true;
			map[nowX][nowY] = queue.poll();
			nowX += dirX[d];
			nowY += dirY[d];
		}
	}
	
	private boolean isIn(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}
	
	private void printResult() throws IOException {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				bw.write(map[i][j] + " ");
			}
			bw.write("\n");
		}	
		
	}
}

