import java.io.*;
import java.util.*;

class Fish implements Comparable<Fish> {
    int x;
    int y;
    int distance;

    Fish(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    @Override
    public int compareTo(Fish other) {
        if (this.distance == other.distance) {
            if (this.x == other.x) {
                return this.y - other.y;
            }
            return this.x - other.x;
        }
        return this.distance - other.distance;
    }
}

class Shark {
    int x;
    int y;
    int size;
    int eatCount;
    int time;

    Shark(int x, int y, int size, int eatCount, int time) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.eatCount = eatCount;
        this.time = time;
    }
}

public class Main {
    int[] dirR = {-1, 0, 1, 0}; // 상, 우, 하, 좌
    int[] dirC = {0, 1, 0, -1};
    int N; // 가로, 세로 크기
    int[][] map;

    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];

        int sharkX = 0;
        int sharkY = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }

        int size = 2;
        int eatCount = 0;
        int time = 0;

        while (true) {
            Fish target = findTarget(sharkX, sharkY, size);
            if (target == null) {
                break;
            }

            int targetX = target.x;
            int targetY = target.y;
            int targetDistance = target.distance;

            map[targetX][targetY] = 0; // 물고기를 잡아먹음

            eatCount++;
            if (eatCount == size) {
                size++;
                eatCount = 0;
            }

            time += targetDistance;

            sharkX = targetX;
            sharkY = targetY;
        }

        bw.write(time + "\n");

        bw.flush();
        bw.close();
    }

    private Fish findTarget(int startX, int startY, int size) {
        boolean[][] visited = new boolean[N][N];
        visited[startX][startY] = true;

        PriorityQueue<Fish> fishes = new PriorityQueue<>();
        int maxDistance = Integer.MAX_VALUE;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int distance = current[2];

            if (distance > maxDistance) {
                break; // 최단 거리보다 큰 거리로 이동하면 종료
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dirR[i];
                int ny = y + dirC[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || map[nx][ny] > size) {
                    continue;
                }

                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny, distance + 1});

                if (map[nx][ny] > 0 && map[nx][ny] < size) {
                    fishes.offer(new Fish(nx, ny, distance + 1));
                    maxDistance = distance + 1; // 최단 거리 갱신
                }
            }
        }

        return fishes.poll();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
