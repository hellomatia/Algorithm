import java.io.*;
import java.util.*;

class Map {
    private HashMap<Integer, Integer> map;
    Map() {
        map = new HashMap<>();
    }

    public void add(int key) {
        if (map.containsKey(key)) {
            map.replace(key, map.get(key) + 1);
        } else {
            map.put(key, 1);
        }
    }

    public int getCount(int key) {
        if (!map.containsKey(key)) {
            return 0;
        }
        return map.get(key);
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;
    private static Map Ns;
    private static int[] Ms;


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        initN();
        initM();
        printResult();
        bw.flush();
        bw.close();
    }

    private void initN() throws IOException {
        N = Integer.parseInt(bf.readLine());
        Ns = new Map();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            Ns.add(Integer.parseInt(st.nextToken()));
        }
    }

    private void initM() throws IOException {
        M = Integer.parseInt(bf.readLine());
        Ms = new int[M];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
            Ms[i] = Integer.parseInt(st.nextToken());
        }
    }

    private void printResult() throws IOException {
        for (int key : Ms) {
            bw.write(Ns.getCount(key) + " ");
        }
        bw.write("\n");
    }
}
