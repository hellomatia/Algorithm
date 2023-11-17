import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;
    private static Map<Integer, String> indexKeyName;
    private static Map<String, Integer> nameKeyIndex;
    private static List<String> names;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int i = 0; i < M; i++) {
            findNameAndIndex();
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        initNandM();
        initNames();
    }

    private void initNandM() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }

    private void initNames() throws IOException {
        indexKeyName = new HashMap<>();
        nameKeyIndex = new HashMap<>();
        for (int index = 1; index <= N; index++) {
            String name = bf.readLine();
            indexKeyName.put(index, name);
            nameKeyIndex.put(name, index);
        }
    }

    private void findNameAndIndex() throws IOException {
        String string = bf.readLine();
        if (string.matches("^[\\d]*$")) {
            printResult(indexKeyName.get(Integer.parseInt(string)));
            return;
        }
        printResult(String.valueOf(nameKeyIndex.get(string)));
    }

    private void printResult(String result) throws IOException {
        bw.write(result + "\n");
    }
}