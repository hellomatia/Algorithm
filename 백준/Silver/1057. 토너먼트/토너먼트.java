import java.io.*;
import java.util.StringTokenizer;

public class Main {
    int N; // 참가자 수
    int kim, lim; // 김씨와 임씨의 번호
    int count;
    int maxCount;
    boolean isOdd;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        kim = Integer.parseInt(st.nextToken());
        lim = Integer.parseInt(st.nextToken());

        runTournament();

        bw.write(count+"\n");
        
        bw.flush();
        bw.close();
    }

    public void runTournament() {
        count++;
        if(kim==lim) {
            count--;
            return;
        }

        kim = (kim+1)/2;
        lim = (lim+1)/2;

        runTournament();

    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}