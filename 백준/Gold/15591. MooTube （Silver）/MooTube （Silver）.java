import java.io.*;
import java.util.*;

class USADO {
    private int video1;
    private int video2;
    private int usado;

    USADO(int video1, int video2, int usado) {
        this.video1 = video1;
        this.video2 = video2;
        this.usado = usado;
    }

    public int getVideo1() {
        return video1;
    }

    public int getVideo2() {
        return video2;
    }

    public int getUsado() {
        return usado;
    }
}

public class Main {
    static final int INF = Integer.MAX_VALUE;

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[][] videoUSADO = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(videoUSADO[i], INF);
        }

        Queue<USADO> queue = new LinkedList<>();

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int video1 = Integer.parseInt(st.nextToken());
            int video2 = Integer.parseInt(st.nextToken());
            int USADO = Integer.parseInt(st.nextToken());

            videoUSADO[video1][video2] = USADO;
            videoUSADO[video2][video1] = USADO;

            queue.offer(new USADO(video1, video2, USADO));
        }

        while (!queue.isEmpty()) {
            USADO now = queue.poll();

            int start = now.getVideo1();
            int via = now.getVideo2();

            for (int i = 1; i <= N; i++) {
                if (start == i) {
                    continue;
                }

                if (videoUSADO[start][i] == INF && videoUSADO[via][i] != INF) {
                    videoUSADO[start][i] = Math.min(now.getUsado(), videoUSADO[via][i]);
                    videoUSADO[i][start] = videoUSADO[start][i];
                }
            }

            start = now.getVideo2();
            via = now.getVideo1();
            for (int i = 1; i <= N; i++) {
                if (start == i) {
                    continue;
                }

                if (videoUSADO[start][i] == INF && videoUSADO[via][i] != INF) {
                    videoUSADO[start][i] = Math.min(now.getUsado(), videoUSADO[via][i]);
                    videoUSADO[i][start] = videoUSADO[start][i];
                }
            }
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(bf.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            int recommendedVideoCount = (int) Arrays.stream(videoUSADO[v])
                    .filter(it -> (it >= k && it != INF))
                    .count();
            bw.write(recommendedVideoCount + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {

        new Main().solution();

    }
}