import java.io.*;
import java.util.StringTokenizer;

public class Main {
    String A;
    String B;
    int count;
    int minScore = Integer.MAX_VALUE;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        A = st.nextToken();
        B = st.nextToken();

        count = B.length() - A.length() + 1;
        getMinScore();

        bw.write(minScore+"\n");


        bw.flush();
        bw.close();
    }

    public void getMinScore() {

        for(int i=0; i<count; i++) {
            minScore = Math.min(minScore, getScore(i));
        }

    }

    public int getScore(int start) {
        int score = 0;

        for(int i=0; i<A.length(); i++){
            if(A.charAt(i)!=B.charAt(start+i)) score++;
        }

        return score;
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}