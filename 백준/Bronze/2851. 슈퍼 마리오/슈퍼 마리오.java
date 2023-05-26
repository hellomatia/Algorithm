import java.io.*;

public class Main {

    int[] mushroom = new int[10];

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int sum = 0;
        int score = 0;
        int minGap = Integer.MAX_VALUE;

        for(int i=0; i<10; i++) {
            mushroom[i] = Integer.parseInt(bf.readLine());
            sum += mushroom[i];
            int gap = Math.abs(100-sum);

            if(gap<=minGap){
                minGap = gap;
                score = sum;
            }
        }

        bw.write(score+"\n");


        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}