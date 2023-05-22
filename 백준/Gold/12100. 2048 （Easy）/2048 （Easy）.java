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
        boolean[][] hasNum = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                gameMap[i][j] = Integer.parseInt(st.nextToken());
                if (gameMap[i][j] != 0) hasNum[i][j] = true;
            }
        }

        playGame(gameMap, hasNum, 0);


        bw.write(maxNum + "\n");


        bw.flush();
        bw.close();
    }
    public void playGame(int[][] gameMap, boolean[][] hasNum, int count) {

        if (count == 5) {
            calculationScore(gameMap, hasNum);
            return;
        }

        playGameUp(gameMap, hasNum, count);
        playGameDown(gameMap, hasNum, count);
        playGameLeft(gameMap, hasNum, count);
        playGameRight(gameMap, hasNum, count);


    }

    public void playGameUp(int[][] gameMap, boolean[][] hasNum, int count) {

        int[][] newMap = new int[N][N];
        boolean[][] newHasNum = new boolean[N][N];

        for(int i=0; i<N; i++) {
            newMap[i] = gameMap[i].clone();
        }

        for(int i=0; i<N; i++) {
            newHasNum[i] = hasNum[i].clone();
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (newHasNum[j][i]) {
                    queue.offer(newMap[j][i]);
                    newMap[j][i] = 0;
                    newHasNum[j][i] = false;
                }
            }
            int idx = 0;
            while (!queue.isEmpty()) {
                newMap[idx][i] = queue.poll();
                newHasNum[idx][i] = true;
                if (!queue.isEmpty() && newMap[idx][i] == queue.peek()) {
                    newMap[idx][i] += queue.poll();
                }
                idx++;
            }
        }
        playGame(newMap, newHasNum, count+1);
    }
    public void playGameDown(int[][] gameMap, boolean[][] hasNum, int count) {

        int[][] newMap = new int[N][N];
        boolean[][] newHasNum = new boolean[N][N];

        for(int i=0; i<N; i++) {
            newMap[i] = gameMap[i].clone();
        }

        for(int i=0; i<N; i++) {
            newHasNum[i] = hasNum[i].clone();
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j >= 0; j--) {
                if (newHasNum[j][i]) {
                    queue.offer(newMap[j][i]);
                    newMap[j][i] = 0;
                    newHasNum[j][i] = false;
                }
            }
            int idx = N - 1;
            while (!queue.isEmpty()) {
                newMap[idx][i] = queue.poll();
                newHasNum[idx][i] = true;
                if (!queue.isEmpty() && newMap[idx][i] == queue.peek()) {
                    newMap[idx][i] += queue.poll();
                }
                idx--;
            }
        }
        playGame(newMap, newHasNum, count+1);
    }
    public void playGameLeft(int[][] gameMap, boolean[][] hasNum, int count) {

        int[][] newMap = new int[N][N];
        boolean[][] newHasNum = new boolean[N][N];

        for(int i=0; i<N; i++) {
            newMap[i] = gameMap[i].clone();
        }

        for(int i=0; i<N; i++) {
            newHasNum[i] = hasNum[i].clone();
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (newHasNum[i][j]) {
                    queue.offer(newMap[i][j]);
                    newMap[i][j] = 0;
                    newHasNum[i][j] = false;
                }
            }
            int idx = 0;
            while (!queue.isEmpty()) {
                newMap[i][idx] = queue.poll();
                newHasNum[i][idx] = true;
                if (!queue.isEmpty() && newMap[i][idx] == queue.peek()) {
                    newMap[i][idx] += queue.poll();
                }
                idx++;
            }
        }
        playGame(newMap, newHasNum, count+1);
    }
    public void playGameRight(int[][] gameMap, boolean[][] hasNum, int count) {

        int[][] newMap = new int[N][N];
        boolean[][] newHasNum = new boolean[N][N];

        for(int i=0; i<N; i++) {
            newMap[i] = gameMap[i].clone();
        }

        for(int i=0; i<N; i++) {
            newHasNum[i] = hasNum[i].clone();
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j >= 0; j--) {
                if (newHasNum[i][j]) {
                    queue.offer(newMap[i][j]);
                    newMap[i][j] = 0;
                    newHasNum[i][j] = false;
                }
            }
            int idx = N - 1;
            while (!queue.isEmpty()) {
                newMap[i][idx] = queue.poll();
                newHasNum[i][idx] = true;
                if (!queue.isEmpty() && newMap[i][idx] == queue.peek()) {
                    newMap[i][idx] += queue.poll();
                }
                idx--;
            }
        }
        playGame(newMap, newHasNum, count+1);
    }

    public void calculationScore(int[][] newMap, boolean[][] newHasNum) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (newHasNum[i][j]) {
                    maxNum = Math.max(newMap[i][j], maxNum);
                }
            }
        }
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}