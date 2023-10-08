import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean[] knowTruth = new boolean[N + 1];

        st = new StringTokenizer(bf.readLine());
        int numOfKnowTruth = Integer.parseInt(st.nextToken());

        for (int i = 0; i < numOfKnowTruth; i++) {
            int num = Integer.parseInt(st.nextToken());
            knowTruth[num] = true;
        }

        ArrayList<Integer>[] parties = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            parties[i] = new ArrayList<>();
        }

        Queue<Integer> canGoPartyQ = new LinkedList<>();

        boolean[] canGo = new boolean[M];
        Arrays.fill(canGo, true);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());

            int numOfPeople = Integer.parseInt(st.nextToken());

            for (int j = 0; j < numOfPeople; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (knowTruth[num]) {
                    canGo[i] = false;
                }
                parties[i].add(num);
            }

            if (!canGo[i]) {
                for (int num : parties[i]) {
                    knowTruth[num] = true;
                }
            } else {
                canGoPartyQ.offer(i);
            }
        }

        int countCanGoParty = canGoPartyQ.size();
        int count = countCanGoParty;

        while (count-- > 0) {

            int now = canGoPartyQ.poll();

            for (int num : parties[now]) {
                if (knowTruth[num]) {
                    canGo[now] = false;
                    break;
                }
            }

            if (!canGo[now]) {
                for (int num : parties[now]) {
                    knowTruth[num] = true;
                }
            } else {
                canGoPartyQ.offer(now);
            }

            if (count == 0) {
                if (countCanGoParty == canGoPartyQ.size()) {
                    break;
                } else {
                    countCanGoParty = canGoPartyQ.size();
                    count = countCanGoParty;
                }
            }
        }

        bw.write(countCanGoParty + "\n");

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}