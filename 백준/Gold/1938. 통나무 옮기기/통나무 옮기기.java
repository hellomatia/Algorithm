import java.util.*;
import java.io.*;

public class Main {

    class Train {

        int x;
        int y;
        int count;
        boolean vertical;

        Train(int x, int y, boolean vertical, int count) {
            this.x = x;
            this.y = y;
            this.vertical = vertical;
            this.count = count;
        }

        public boolean canUp(char[][] map) {
            if (vertical) {
                if (x - 2 >= 0 && map[x - 2][y] != '1') {
                    return true;
                }
            } else {
                if (x - 1 >= 0
                        && map[x - 1][y] != '1'
                        && map[x - 1][y - 1] != '1'
                        && map[x - 1][y + 1] != '1') {
                    return true;
                }
            }
            return false;
        }

        boolean canDown(char[][] map) {
            if (vertical) {
                if (x + 2 < map.length && map[x + 2][y] != '1') {
                    return true;
                }
            } else {
                if (x + 1 < map.length
                        && map[x + 1][y] != '1'
                        && map[x + 1][y - 1] != '1'
                        && map[x + 1][y + 1] != '1') {
                    return true;
                }
            }
            return false;
        }

        boolean canLeft(char[][] map) {
            if (vertical) {
                if (y - 1 >= 0
                        && map[x][y - 1] != '1'
                        && map[x - 1][y - 1] != '1'
                        && map[x + 1][y - 1] != '1') {
                    return true;
                }
            } else {
                if (y - 2 >= 0 && map[x][y - 2] != '1') {
                    return true;
                }
            }

            return false;
        }

        boolean canRight(char[][] map) {
            if (vertical) {
                if (y + 1 < map.length
                        && map[x][y + 1] != '1'
                        && map[x - 1][y + 1] != '1'
                        && map[x + 1][y + 1] != '1') {
                    return true;
                }
            } else {
                if (y + 2 < map.length && map[x][y + 2] != '1') {
                    return true;
                }
            }

            return false;
        }

        boolean canTurn(char[][] map) {
            if (vertical) {
                if (y - 1 >= 0 && y + 1 < map.length) {
                    if (map[x][y - 1] != '1' &&
                            map[x + 1][y - 1] != '1' &&
                            map[x - 1][y - 1] != '1' &&
                            map[x][y + 1] != '1' &&
                            map[x + 1][y + 1] != '1' &&
                            map[x - 1][y + 1]  != '1') {
                        return true;
                    }
                }
            } else {
                if (x - 1 >= 0 && x + 1 < map.length) {
                    if (map[x - 1][y] != '1' &&
                            map[x - 1][y + 1] != '1' &&
                            map[x - 1][y - 1] != '1' &&
                            map[x + 1][y] != '1' &&
                            map[x + 1][y + 1] != '1' &&
                            map[x + 1][y - 1] != '1') {
                        return true;
                    }
                }
            }
            return false;
        }

        public void goUp() {
            x -= 1;
        }

        public void goDown() {
            x += 1;
        }

        public void goLeft() {
            y -= 1;
        }

        public void goRight() {
            y += 1;
        }

        public void goTurn() {
            vertical = !vertical;
        }

        public Train clone() {
            return new Train(x, y, vertical, count);
        }

    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private char[][] map;
    private Train train;
    private Train arrive;
    private int[][] visited1;
    private int[][] visited2;
    private boolean canArrive;
    private int ans;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        calcAns();
        printAns();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            String rowValue = bf.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = rowValue.charAt(j);
            }
        }

        initTrain();
        initArrive();

        ans = Integer.MAX_VALUE;
        visited1 = new int[N][N];
        visited2 = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited1[i], Integer.MAX_VALUE);
            Arrays.fill(visited2[i], Integer.MAX_VALUE);
        }
    }

    private void initTrain() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'B') {
                    if (j + 1 < N && map[i][j + 1] == 'B') {
                        train = new Train(i, j + 1, false, 0);
                    } else if (i + 1 < N && map[i + 1][j] == 'B') {
                        train = new Train(i + 1, j, true, 0);
                    }
                    return;
                }
            }
        }
    }

    private void initArrive() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'E') {
                    if (j + 1 < N && map[i][j + 1] == 'E') {
                        arrive = new Train(i, j + 1, false, 0);
                    } else if (i + 1 < N && map[i + 1][j] == 'E') {
                        arrive= new Train(i + 1, j, true, 0);
                    }
                    return;
                }
            }
        }
    }

    private void calcAns() {
        Queue<Train> queue = new ArrayDeque<>();
        queue.offer(train);

        while (!queue.isEmpty()) {
            Train now = queue.poll();

            if (now.x == arrive.x
                    && now.y == arrive.y
                    && now.vertical == arrive.vertical) {
                canArrive = true;
                ans = Math.min(ans, now.count);
            }

            if (now.vertical) {
                if (now.canUp(map)
                        && visited1[now.x - 1][now.y] > now.count + 1) {
                    visited1[now.x - 1][now.y] = now.count + 1;
                    Train nextTrain = now.clone();
                    nextTrain.goUp();
                    nextTrain.count += 1;
                    queue.offer(nextTrain);
                }
                if (now.canDown(map)
                        && visited1[now.x + 1][now.y] > now.count + 1) {
                    visited1[now.x + 1][now.y] = now.count + 1;
                    Train nextTrain = now.clone();
                    nextTrain.goDown();
                    nextTrain.count += 1;
                    queue.offer(nextTrain);
                }
                if (now.canLeft(map)
                        && visited1[now.x][now.y - 1] > now.count + 1) {
                    visited1[now.x][now.y - 1] = now.count + 1;
                    Train nextTrain = now.clone();
                    nextTrain.goLeft();
                    nextTrain.count += 1;
                    queue.offer(nextTrain);
                }
                if (now.canRight(map)
                        && visited1[now.x][now.y + 1] > now.count + 1) {
                    visited1[now.x][now.y + 1] = now.count + 1;
                    Train nextTrain = now.clone();
                    nextTrain.goRight();
                    nextTrain.count += 1;
                    queue.offer(nextTrain);
                }
                if (now.canTurn(map)
                        && visited2[now.x][now.y] > now.count + 1) {
                    visited2[now.x][now.y] = now.count + 1;
                    Train nextTrain = now.clone();
                    nextTrain.goTurn();
                    nextTrain.count += 1;
                    queue.offer(nextTrain);
                }
            } else {
                if (now.canUp(map)
                        && visited2[now.x - 1][now.y] > now.count + 1) {
                    visited2[now.x - 1][now.y] = now.count + 1;
                    Train nextTrain = now.clone();
                    nextTrain.goUp();
                    nextTrain.count += 1;
                    queue.offer(nextTrain);
                }
                if (now.canDown(map)
                        && visited2[now.x + 1][now.y] > now.count + 1) {
                    visited2[now.x + 1][now.y] = now.count + 1;
                    Train nextTrain = now.clone();
                    nextTrain.goDown();
                    nextTrain.count += 1;
                    queue.offer(nextTrain);
                }
                if (now.canLeft(map)
                        && visited2[now.x][now.y - 1] > now.count + 1) {
                    visited2[now.x][now.y - 1] = now.count + 1;
                    Train nextTrain = now.clone();
                    nextTrain.goLeft();
                    nextTrain.count += 1;
                    queue.offer(nextTrain);
                }
                if (now.canRight(map)
                        && visited2[now.x][now.y + 1] > now.count + 1) {
                    visited2[now.x][now.y + 1] = now.count + 1;
                    Train nextTrain = now.clone();
                    nextTrain.goRight();
                    nextTrain.count += 1;
                    queue.offer(nextTrain);
                }
                if (now.canTurn(map)
                        && visited1[now.x][now.y] > now.count + 1) {
                    visited1[now.x][now.y] = now.count + 1;
                    Train nextTrain = now.clone();
                    nextTrain.goTurn();
                    nextTrain.count += 1;
                    queue.offer(nextTrain);
                }
            }
        }
    }

    private void printAns() throws IOException {
        if (canArrive) {
            bw.write(ans + "\n");
        } else {
            bw.write(0 + "\n");
        }
        bw.flush();
    }

}