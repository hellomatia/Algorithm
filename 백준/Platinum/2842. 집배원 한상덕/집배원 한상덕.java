import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, houseCnt = 0, ans = Integer.MAX_VALUE;
    static int left = 0, right = 0;
    static char[][] map;       
    static int[][] height;   
    static int[] list; 
    static Loc post = null;
    static int[][] dt = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int min = Integer.MAX_VALUE, max = 0;
        HashSet<Integer> set = new HashSet<>();
        N = Integer.parseInt(br.readLine());

        height = new int[N][N];
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = s.charAt(j);
                if (c == 'P') {
                    post = new Loc(i, j);
                } else if (c == 'K') {
                    houseCnt++;
                }
                map[i][j] = c;
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                height[i][j] = num;
                set.add(num);

                if (map[i][j] != '.') {
                    max = Math.max(num, max);
                    min = Math.min(num, min);
                }
            }
        }

        int idx = 0;
        list = new int[set.size()];
        for (int i : set) {
            list[idx++] = i;
        }

        Arrays.sort(list);
        for (int i = 0; i < list.length; i++) {
            if (max == list[i]) right = i;
            if (min == list[i]) min = i;
        }

        while (left <= right && right < list.length && min >= left) {
            if (bfs()) {
                ans = Math.min(list[right] - list[left], ans);
                left++;
            } else {
                right++;
            }
        }

        System.out.println(ans);
        br.close();
    }

    public static boolean bfs() {

        boolean[][] visited = new boolean[N][N];
        int cnt = 0;

        Queue<Loc> q = new LinkedList<>();
        q.add(post);
        visited[post.x][post.y] = true;

        while (!q.isEmpty()) {
            Loc loc = q.poll();
            for (int d = 0; d < 8; d++) {
                int dx = loc.x + dt[d][0];
                int dy = loc.y + dt[d][1];

                if (check(dx, dy) && !visited[dx][dy]
                        && height[dx][dy] >= list[left] && height[dx][dy] <= list[right]) {

                    visited[dx][dy] = true;
                    q.add(new Loc(dx,dy));

                    if (map[dx][dy] == 'K') {
                        cnt++;
                    }
                }
            }
        }
        if (cnt == houseCnt) {
            return true;
        }
        return false;
    }

    public static boolean check(int x, int y) {
        if (x >= 0 && x < N && y >= 0 && y < N) return true;
        return false;
    }

    public static class Loc {
        int x, y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}