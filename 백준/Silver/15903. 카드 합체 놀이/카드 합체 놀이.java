import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int cardCount = Integer.parseInt(st.nextToken());
        int cardCombinationCount = Integer.parseInt(st.nextToken());

        long[] cardArr = new long[cardCount];

        st = new StringTokenizer(bf.readLine(), " ");

        for(int i=0; i<cardCount; i++){
            cardArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cardArr);

        for(int i=0; i<cardCombinationCount; i++) {
            long temp = cardArr[0] + cardArr[1];
            cardArr[0] = temp;
            cardArr[1] = temp;
            Arrays.sort(cardArr);
        }

        long minSum = 0;

        for(int i=0; i<cardCount; i++){
            minSum += cardArr[i];
        }

        bw.write(String.valueOf(minSum));

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}