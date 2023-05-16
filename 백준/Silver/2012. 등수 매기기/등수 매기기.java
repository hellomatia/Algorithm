import java.io.*;
import java.util.*;

public class Main {

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int studentNum = Integer.parseInt(bf.readLine());

        int[] studentExpectedScore = new int[studentNum];

        for(int i=0; i<studentNum; i++) {
            studentExpectedScore[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(studentExpectedScore);

        long dissatisfaction = 0;
        for(int i=0; i<studentNum; i++) {
            dissatisfaction += Math.abs((i+1)-studentExpectedScore[i]);
        }

        bw.write(String.valueOf(dissatisfaction));

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}