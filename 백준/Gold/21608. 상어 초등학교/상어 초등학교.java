import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
class Point {
    int x;
    int y;
    int favoriteStudentCount;
    int emptyCount;

    Point(int x, int y) {
        this.x =x;
        this.y = y;
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static int[][] classRoom;
    private static List<Integer>[] preferredStudentList;
    private static int[] dirX = {0, 0, 1, -1};
    private static int[] dirY = {1, -1, 0, 0};
    private static PriorityQueue<Point> pq = new PriorityQueue<>(((o1, o2) -> {
        if (o2.favoriteStudentCount == o1.favoriteStudentCount) {
            if (o1.emptyCount == o2.emptyCount) {
                if (o1.x == o2.x) {
                    return o1.y - o2.y;
                }
                return o1.x - o2.x;
            }
            return o2.emptyCount - o1.emptyCount;
        }
        return o2.favoriteStudentCount - o1.favoriteStudentCount;
    }));


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(getResult());
    }

    private void init() throws IOException {
        initN();
        initClassRoom();
        initPreferredStudentList();
    }

    private void initN() throws IOException {
        N = Integer.parseInt(bf.readLine());
    }

    private void initClassRoom() {
        classRoom = new int[N][N];
        for (int row = 0; row < N; row++) {
            Arrays.fill(classRoom[row], -1);
        }
    }

    private void initPreferredStudentList() throws IOException {
        preferredStudentList = new List[N * N];
        for (int count = 0; count < N * N; count++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int studentNumber = Integer.parseInt(st.nextToken()) - 1;
            preferredStudentList[studentNumber] = new ArrayList<>();
            for (int studentCount = 0; studentCount < 4; studentCount++) {
                preferredStudentList[studentNumber].add(Integer.parseInt(st.nextToken()) - 1);
            }
            findBestPoint(studentNumber);
            pq.clear();
        }
    }

    private void findBestPoint(int studentNum) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                scanClassRoom(studentNum, new Point(row, col));
            }
        }
        Point bestPoint = pq.poll();
        classRoom[bestPoint.x][bestPoint.y] = studentNum;
    }

    private void scanClassRoom(int studentNum, Point now) {
        if  (classRoom[now.x][now.y] != -1) {
            return;
        }
        for (int dir = 0 ; dir < 4; dir++) {
            int nextX = now.x + dirX[dir];
            int nextY = now.y + dirY[dir];
            if (nextX < 0 || nextY < 0 || N <= nextX || N <= nextY) {
                continue;
            }
            if (classRoom[nextX][nextY] == -1) {
                now.emptyCount++;
            }
            if (preferredStudentList[studentNum].contains(classRoom[nextX][nextY])) {
                now.favoriteStudentCount++;
            }
        }
        pq.offer(now);
    }

    private int getResult() {
        int result = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                result += getScore(row, col);
            }
        }
        return result;
    }

    private int getScore(int x, int y) {
        int count = 0;
        for (int dir = 0 ; dir < 4; dir++) {
            int nextX = x + dirX[dir];
            int nextY = y + dirY[dir];
            if (nextX < 0 || nextY < 0 || N <= nextX || N <= nextY) {
                continue;
            }
            if (preferredStudentList[classRoom[x][y]].contains(classRoom[nextX][nextY])) {
                count++;
            }
        }
        if (count == 0) {
            return 0;
        }
        if (count == 1) {
            return 1;
        }
        if (count == 2) {
            return 10;
        }
        if (count == 3) {
            return 100;
        }
        return 1000;
    }

    private void printClassRoom() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                System.out.print((classRoom[row][col] + 1) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}