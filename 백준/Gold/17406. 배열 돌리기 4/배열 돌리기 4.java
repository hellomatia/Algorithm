import java.io.*;
import java.util.*;

class Turn {
    int r;
    int c;
    int s;
    Turn(int r, int c, int s) {
        this.r = r;
        this.c = c;
        this.s = s;
    }
}

public class Main {
    int N, M; // 세로, 가로
    int k; // 회전 횟수
    int[][] map;
    int[] turnOrder;
    boolean[] isUsed;
    ArrayList<Turn> turns;
    int minScore = Integer.MAX_VALUE;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        turns = new ArrayList<>();
        turnOrder = new int[k];
        isUsed = new boolean[k];

        for(int i=0; i<k; i++) {
            st = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            turns.add(new Turn(r,c,s));
        }

        setTurnOrder(0);

        bw.write(minScore + "\n");


        bw.flush();
        bw.close();
    }

    public void setTurnOrder(int count) {
        if(count==k) {
            minScore = Math.min(score(), minScore);
        }

        for(int i=0; i<k; i++) {
            if(!isUsed[i]) {
                isUsed[i] = true;
                turnOrder[count] = i;
                setTurnOrder(count+1);
                isUsed[i] = false;
            }
        }
    }

    public int score() {
        int[][] newMap = copyMap();
        for(int i=0; i<k; i++) {
            turnMap(0, turnOrder[i], newMap);
        }

        int score = Integer.MAX_VALUE;
        for(int i=0; i<N; i++) {
            int sum = 0;
            for(int j=0; j<M; j++) {
                sum += newMap[i][j];
            }
            score = Math.min(sum, score);
        }

        return score;
    }

    public void turnMap(int count, int idx, int[][] newMap) {
        int r = turns.get(idx).r;
        int c = turns.get(idx).c;
        int s = turns.get(idx).s;

        if(count == s+1) {
            return;
        }

        int x1 = r-s-1+count;
        int x2 = r+s-1-count;
        int y1 = c-s-1+count;
        int y2 = c+s-1-count;
        

        Queue<Integer> queue = new LinkedList<>();

        for(int i=y1; i<=y2; i++) {
            queue.offer(newMap[x1][i]);
        }
        for(int i=y1+1; i<=y2; i++) {
            newMap[x1][i] = queue.poll();
        }

        for(int i=x1+1; i<=x2; i++) {
            queue.offer(newMap[i][y2]);
        }
        for(int i=x1+1; i<=x2; i++) {
            newMap[i][y2] = queue.poll();
        }

        for(int i=y2-1; i>=y1; i--) {
            queue.offer(newMap[x2][i]);
        }
        for(int i=y2-1; i>=y1; i--) {
            newMap[x2][i] = queue.poll();
        }

        for(int i=x2-1; i>x1; i--) {
            queue.offer(newMap[i][y1]);
        }
        for(int i=x2-1; i>=x1; i--) {
            newMap[i][y1] = queue.poll();
        }

        turnMap(count+1, idx, newMap);



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
