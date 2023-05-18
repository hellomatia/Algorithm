import java.io.*;
import java.util.*;

public class Main {

    int[] num;
    int numCount;
    int[] operator;
    int minNum = Integer.MAX_VALUE;
    int maxNum = Integer.MIN_VALUE;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        numCount = Integer.parseInt(bf.readLine());
        num = new int[numCount];
        operator = new int[4];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i=0; i<numCount; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine());
        for(int i=0; i<4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        dfs(num[0],1);


        bw.write(String.valueOf(maxNum));
        bw.write("\n");
        bw.write(String.valueOf(minNum));


        bw.flush();
        bw.close();
    }

    public void dfs(int number, int index) {
        if(numCount==index) {
            if(number>maxNum) {
                maxNum = number;
            }

            if(number<minNum) {
                minNum = number;
            }
            return;
        }

        for(int i=0; i<4; i++) {
            if(operator[i]==0) continue;

            operator[i]--;

            switch (i) {
                case 0:
                    dfs(number + num[index], index+1);
                    break;
                case 1:
                    dfs(number - num[index], index+1);
                    break;
                case 2:
                    dfs(number * num[index], index+1);
                    break;
                case 3:
                    dfs(number / num[index], index+1);
                    break;
            }

            operator[i]++;
        }
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}