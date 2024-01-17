import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N, M;
    private static Map<String, String> map;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int i = 0; i < M; i++) {
            printResult(calcResult(bf.readLine()));
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            map.put(
                    st.nextToken(),
                    st.nextToken()
            );
        }
    }

    private String calcResult(String site) {
        return map.get(site);
    }

    private void printResult(String result) throws IOException {
        bw.write(result + "\n");
    }
}