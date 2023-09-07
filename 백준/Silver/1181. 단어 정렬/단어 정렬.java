import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void solution() throws IOException {

        int N = Integer.parseInt(bf.readLine());

        PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) -> {

            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }

            return o1.length() - o2.length();
        });

        Set<String> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String word = bf.readLine();
            if(set.add(word)) {
                pq.offer(word);
            }
        }

        while(!pq.isEmpty()) {
            bw.write(pq.poll() + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}