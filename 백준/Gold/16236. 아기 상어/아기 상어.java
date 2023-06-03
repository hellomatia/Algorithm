import java.io.*;
import java.util.*;

class Fish implements Comparable<Fish> {
    int x;
    int y;
    int d;

    Fish(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }

    @Override
    public int compareTo(Fish other) {
        if(this.d == other.d) {
            if(this.x == other.x) {
                return this.y - other.y;
            }
            return this.x - other.x;
        }
        return this.d - other.d;
    }
}

class Shark{
    int x;
    int y;
    int size;
    int eatCount;
    Shark(int x, int y, int size, int eatCount) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.eatCount = eatCount;
    }
}

public class Main {
    int[] dirR = {-1, 0, 1, 0}; // 상, 우, 하, 좌
    int[] dirC = {0, 1, 0, -1};
    Shark shark;
    PriorityQueue<Integer> fishSize;
    int N; // 가로, 세로 크기
    int[][] map;
    boolean[][] visit;
    int time;
    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        fishSize = new PriorityQueue<>();

        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        visit = new boolean[N][N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==9) shark = new Shark(i,j,2, 0);
                if(0<map[i][j]&&map[i][j]<=6) {
                    fishSize.offer(map[i][j]);
                }
            }
        }

        exploreFish();


        bw.write(time + "\n");


        bw.flush();
        bw.close();
    }

    public void exploreFish() {
        while(true) {
            Queue<int[]> queue = new LinkedList<>();
            PriorityQueue<Fish> fishes = new PriorityQueue<>();
            boolean[][] visited = new boolean[N][N];
            queue.add(new int[]{shark.x, shark.y, 0});
            visited[shark.x][shark.y] = true;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                int cnt = current[2];

                for (int i = 0; i < 4; i++) {
                    int x2 = x + dirR[i];
                    int y2 = y + dirC[i];
                    if (x2 < 0 || y2 < 0 || N <= x2 || N <= y2 || map[x2][y2] > shark.size || visited[x2][y2]) continue;
                    visited[x2][y2] = true;
                    if (map[x2][y2] != 0 && map[x2][y2] < shark.size)
                        fishes.offer(new Fish(x2, y2, cnt + 1));
                    queue.add(new int[]{x2, y2, cnt + 1});
                }
            }

            if(!fishes.isEmpty()) {
                Fish selected = fishes.poll();
                map[shark.x][shark.y] = 0;
                map[selected.x][selected.y] = 9;
                shark.x = selected.x;
                shark.y = selected.y;
                shark.eatCount++;
                if (shark.eatCount == shark.size) {
                    shark.eatCount = 0;
                    shark.size++;
                }
                time += selected.d;
            } else {
                break;
            }
        }
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}