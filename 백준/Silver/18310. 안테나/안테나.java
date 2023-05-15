import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numOfHouse = Integer.parseInt(bf.readLine());
        int[] houseArr = new int[numOfHouse];

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        
        for(int i=0; i<numOfHouse; i++) {
            houseArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(houseArr);

        bw.write(String.valueOf(houseArr[(numOfHouse-1)/2]));

        bw.flush();
        bw.close();


    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}