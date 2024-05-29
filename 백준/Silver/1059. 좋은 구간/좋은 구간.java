import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    private final static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private final TreeSet<Integer> treeSet = new TreeSet<>();
    private int n;


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        int size = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < size; i++) {
            treeSet.add(Integer.parseInt(st.nextToken()));
        }
        n = Integer.parseInt(bf.readLine());
    }

    private String calcAns() {
        if (treeSet.contains(n)) {
            return "0";
        }
        Integer start = treeSet.lower(n);
        Integer end = treeSet.higher(n);
        if (start == null) {
            start = 0;
        }
        return (n - start - 1) * (end - n) + (end - n - 1) + "";
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}