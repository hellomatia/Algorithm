import java.io.*;
import java.util.*;
import java.util.stream.Stream;

class Tree {
    int x;
    int y;
    int age;

    Tree(int x, int y, int age) {
        this.x = x;
        this.y = y;
        this.age = age;
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M, K;
    private static int[][] A;
    private static int[][] map;
    private static PriorityQueue<Tree> trees = new PriorityQueue<>((o1, o2) -> {
        return o1.age - o2.age;
    });
    private static Queue<Tree> deadTrees = new LinkedList<>();
    private static int[] dirX = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dirY = {0, 1, 1, 1, 0, -1, -1, -1};


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int year = 0; year < K; year++) {
            oneYearLatter();
        }
        printResult();
    }

    private void init() throws IOException {
        initNMK();
        initA();
        initTrees();
        initMap();
    }

    private void initNMK() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    private void initA() throws IOException {
        A = new int[N][N];
        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int col = 0; col < N; col++) {
                A[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private void initTrees() throws IOException {
        for (int count = 0; count < M; count++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees.offer(new Tree(x, y, age));
        }
    }

    private void initMap() {
        map = new int[N][N];
        for (int row = 0; row < N; row++) {
            Arrays.fill(map[row], 5);
        }
    }

    private void oneYearLatter() {
        spring();
        summer();
        fall();
        winter();
    }

    private void spring() {
        Queue<Tree> growTrees = new LinkedList<>();

        Stream.generate(trees::poll)
                .limit(trees.size())
                .forEach(tree -> {
                    if (tree.age <= map[tree.x][tree.y]) {
                        map[tree.x][tree.y] -= tree.age;
                        tree.age++;
                        growTrees.offer(tree);
                    }
                    else {
                        deadTrees.offer(tree);
                    }
                });

        growTrees.forEach(tree -> trees.offer(tree));
    }

    private void summer() {
        Stream.generate(deadTrees::poll)
                .limit(deadTrees.size())
                .forEach(tree -> map[tree.x][tree.y] += (tree.age / 2));
    }

    private void fall() {
        Queue<Tree> newTrees = new LinkedList<>();
        trees.stream()
                .filter(tree -> tree.age % 5 == 0)
                .forEach(tree -> {
                    for (int dir = 0; dir < 8; dir++) {
                        int nextX = tree.x + dirX[dir];
                        int nextY = tree.y + dirY[dir];
                        if (nextX < 0 || nextY < 0 || N <= nextX || N <= nextY) {
                            continue;
                        }
                        newTrees.offer(new Tree(nextX, nextY, 1));
                    }
                });
        newTrees.forEach(tree -> trees.offer(tree));
    }

    private void winter() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                map[row][col] += A[row][col];
            }
        }
    }


    private void printResult() throws IOException {
        bw.write(trees.size() + "\n");
        bw.flush();
        bw.close();
    }
}