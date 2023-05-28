import java.io.*;
import java.util.StringTokenizer;

public class Main {

    int N; // 세로
    int M; // 가로
    int D; // 제한 거리
    int[][] map;
    int[] archers;
    int maxScore;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        archers = new int[3];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        placeThreeArchers(0, 0);

        bw.write(maxScore + "\n");


        bw.flush();
        bw.close();
    }

    public void placeThreeArchers(int idx, int count) {
        if(count==3) {
            int[][] copyMap = copyMap();
            simulationDefence(1, copyMap);
            return;
        }

        for(int i=idx; i<M; i++) {
            archers[count] = i;
            placeThreeArchers(i+1,count+1);
        }
    }

    public void simulationDefence(int count, int[][] copyMap) {
        if(count == N+1) {
            maxScore = Math.max(score(copyMap), maxScore);
            return;
        }

        for(int i=0; i<3; i++) {
            int minDistance = Integer.MAX_VALUE;
            int x=100, y=100;
            for(int j=0; j<=N-count; j++) {
                for(int k=M-1; k>=0; k--) {
                    if(copyMap[j][k]==1 || copyMap[j][k]==-count) {
                        int distance = Math.abs((N-count+1-j)) + Math.abs(archers[i]-k);
                        if(distance<=D && minDistance>=distance) {
                            if(minDistance==distance && k>y) continue;
                            minDistance = distance;
                            x = j;
                            y = k;
                        }
                    }
                }
            }
            if(minDistance == Integer.MAX_VALUE) continue;
            copyMap[x][y] = -count;
        }

        simulationDefence(count+1, copyMap);
    }

    public int score(int[][] copyMap) {

        int score = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(copyMap[i][j]<0) score++;
            }
        }

        return score;
    }

    public int[][] copyMap() {
        int[][] copyMap = new int[N][M];
        for(int i=0; i<N; i++) {
            copyMap[i] = map[i].clone();
        }

        return copyMap;
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}

/*

10 10 8
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0


7 6 2
0 1 1 0 1 0
1 1 0 1 0 0
1 0 1 0 0 1
0 1 0 0 1 0
1 0 0 1 0 1
0 0 1 0 1 1
0 1 0 1 1 0
 */