import java.io.*;
import java.util.*;

public class Main {

    int[] num;
    int numCount;
    int[] modify;
    int minNum = Integer.MAX_VALUE;
    int maxNum = Integer.MIN_VALUE;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        numCount = Integer.parseInt(bf.readLine());
        num = new int[numCount];
        modify = new int[4];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i=0; i<numCount; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine());
        for(int i=0; i<4; i++) {
            modify[i] = Integer.parseInt(st.nextToken());
        }

        calculation(modify, num[0], 1);


        bw.write(String.valueOf(maxNum));
        bw.write("\n");
        bw.write(String.valueOf(minNum));


        bw.flush();
        bw.close();
    }

    public void calculation(int[]mod, int number, int depth) {
        if(numCount==depth) {
            if(number>maxNum) {
                maxNum = number;
            }

            if(number<minNum) {
                minNum = number;
            }
            return;
        }

        for(int i=0; i<4; i++) {
            if(mod[i]==0) continue;
            if(i==0) {
                int result;
                result = number + num[depth];
                mod[i]--;
                calculation(mod, result, depth+1);
                mod[i]++;
            } else if(i==1) {
                int result;
                result = number - num[depth];
                mod[i]--;
                calculation(mod, result, depth+1);
                mod[i]++;
            } else if(i==2) {
                int result;
                result = number * num[depth];
                mod[i]--;
                calculation(mod, result, depth+1);
                mod[i]++;
            } else {
                int result;
                result = number / num[depth];
                mod[i]--;
                calculation(mod, result, depth+1);
                mod[i]++;
            }
        }
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}