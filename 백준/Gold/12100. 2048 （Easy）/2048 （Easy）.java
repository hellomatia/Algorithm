import java.io.*;
import java.util.*;

public class Main {
    int N;
    int maxNum = Integer.MIN_VALUE;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(bf.readLine()); // 가로, 세로

        int[][] gameMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                gameMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        playGame(gameMap,0);


        bw.write(maxNum + "\n");


        bw.flush();
        bw.close();
    }
    public void playGame(int[][] gameMap, int count) {

        if (count == 5) {
            calculationScore(gameMap);
            return;
        }

        playGameUp(gameMap, count);
        playGameDown(gameMap, count);
        playGameLeft(gameMap, count);
        playGameRight(gameMap, count);


    }

    public void playGameUp(int[][] gameMap, int count) {

        int[][] newMap = new int[N][N];

        for(int i=0; i<N; i++) {
            newMap[i] = gameMap[i].clone();
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (newMap[j][i]!=0) {
                    queue.offer(newMap[j][i]);
                    newMap[j][i] = 0;
                }
            }
            int idx = 0;
            while (!queue.isEmpty()) {
                newMap[idx][i] = queue.poll();
                if (!queue.isEmpty() && newMap[idx][i] == queue.peek()) {
                    newMap[idx][i] += queue.poll();
                }
                idx++;
            }
        }
        playGame(newMap,count+1);
    }
    public void playGameDown(int[][] gameMap,  int count) {

        int[][] newMap = new int[N][N];

        for(int i=0; i<N; i++) {
            newMap[i] = gameMap[i].clone();
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j >= 0; j--) {
                if (newMap[j][i]!=0) {
                    queue.offer(newMap[j][i]);
                    newMap[j][i] = 0;
                }
            }
            int idx = N - 1;
            while (!queue.isEmpty()) {
                newMap[idx][i] = queue.poll();
                if (!queue.isEmpty() && newMap[idx][i] == queue.peek()) {
                    newMap[idx][i] += queue.poll();
                }
                idx--;
            }
        }
        playGame(newMap, count+1);
    }
    public void playGameLeft(int[][] gameMap, int count) {

        int[][] newMap = new int[N][N];

        for(int i=0; i<N; i++) {
            newMap[i] = gameMap[i].clone();
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (newMap[i][j]!=0) {
                    queue.offer(newMap[i][j]);
                    newMap[i][j] = 0;
                }
            }
            int idx = 0;
            while (!queue.isEmpty()) {
                newMap[i][idx] = queue.poll();
                if (!queue.isEmpty() && newMap[i][idx] == queue.peek()) {
                    newMap[i][idx] += queue.poll();
                }
                idx++;
            }
        }
        playGame(newMap, count+1);
    }
    public void playGameRight(int[][] gameMap, int count) {

        int[][] newMap = new int[N][N];

        for(int i=0; i<N; i++) {
            newMap[i] = gameMap[i].clone();
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j >= 0; j--) {
                if (newMap[i][j]!=0) {
                    queue.offer(newMap[i][j]);
                    newMap[i][j] = 0;
                }
            }
            int idx = N - 1;
            while (!queue.isEmpty()) {
                newMap[i][idx] = queue.poll();
                if (!queue.isEmpty() && newMap[i][idx] == queue.peek()) {
                    newMap[i][idx] += queue.poll();
                }
                idx--;
            }
        }
        playGame(newMap, count+1);
    }

    public void calculationScore(int[][] newMap) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (newMap[i][j]!=0) {
                    maxNum = Math.max(newMap[i][j], maxNum);
                }
            }
        }
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}