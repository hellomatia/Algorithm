import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int numOfDigits = Integer.parseInt(st.nextToken());
        int changeDigitCount = Integer.parseInt(st.nextToken());

        String inputString = bf.readLine();

        char[] charArr = inputString.toCharArray();
        Deque<Character> dq = new ArrayDeque<>();
        for(int i=0; i<charArr.length; i++) {
            while (changeDigitCount>0 && !dq.isEmpty() && dq.getLast()<charArr[i]) {
                dq.removeLast();
                changeDigitCount--;
            }
            dq.addLast(charArr[i]);
        }

        StringBuilder sb = new StringBuilder();

        while (dq.size() > changeDigitCount) {
            sb.append(dq.removeFirst());
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}