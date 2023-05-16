import java.io.*;
import java.util.*;

public class Main {

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int fruitCount = Integer.parseInt(st.nextToken());
        int snakebirdLength = Integer.parseInt(st.nextToken());

        int[] fruit = new int[fruitCount];

        st = new StringTokenizer(bf.readLine());

        for(int i=0; i<fruitCount; i++) {
            fruit[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(fruit);

        int count = 0;

        while(snakebirdLength>=fruit[count]) {
            snakebirdLength++;
            count++;
            if(count>=fruitCount) break;
        }

        bw.write(String.valueOf(snakebirdLength));

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}