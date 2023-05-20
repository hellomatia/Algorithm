import java.io.*;
import java.util.*;

public class Main {
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken()); // N의 약수
        int K = Integer.parseInt(st.nextToken()); // K번째로 작은 약수

        ArrayList<Integer> list = new ArrayList<>();

        for(int i=1; i<=N; i++) {
            int share = N/i;
            int remainder = N%i;
            if(!list.contains(share) && remainder==0) {
                list.add(share);
            }
        }

        Collections.sort(list);

        if(list.isEmpty() || K>list.size()) {
            bw.write("0");
            bw.flush();
            bw.close();
            return;
        }

        bw.write(list.get(K-1)+"\n");



        bw.flush();
        bw.close();
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}