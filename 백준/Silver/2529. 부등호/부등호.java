import java.io.*;
import java.util.StringTokenizer;

public class Main {
    int k; // 부등호 개수
    int n; // 사용하는 정수 개수
    char[] inequalities;
    int[] num;
    boolean[] isUsed = new boolean[10];
    long minNum = Long.MAX_VALUE;
    String minNumStr;
    long maxNum = Long.MIN_VALUE;
    String maxNumStr;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        k = Integer.parseInt(bf.readLine());
        inequalities = new char[k];

        n = k+1;
        num = new int[n];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for(int i=0; i<k; i++) {
            String str = st.nextToken();
            inequalities[i] = str.charAt(0);
        }

        randomNum(0);


        bw.write(maxNumStr+"\n");
        bw.write(minNumStr);

        bw.flush();
        bw.close();
    }

    public void randomNum(int count) {
        if(count==n){
            if(satisfiesCondition()) {
                long tempNum = 0;

                for(int i=0; i<n; i++){
                    tempNum *= 10;
                    tempNum += num[i];
                }

                if(minNum > tempNum) {
                    minNum = tempNum;
                    StringBuilder sb = new StringBuilder();
                    for(int i=0; i<n; i++) {
                        sb.append(num[i]);
                    }
                    minNumStr = sb.toString();
                }

                if(maxNum < tempNum) {
                    maxNum = tempNum;
                    StringBuilder sb = new StringBuilder();
                    for(int i=0; i<n; i++) {
                        sb.append(num[i]);
                    }
                    maxNumStr = sb.toString();
                }
            }
            return;
        }

        for(int i=0; i<10; i++) {
            if(!isUsed[i]){
                num[count] = i;
                isUsed[i] = true;
                randomNum(count+1);
                isUsed[i] = false;
            }
        }

    }

    public boolean satisfiesCondition() {
        int count = 0;

        while(count < k) {
            switch(inequalities[count]) {
                case '<':
                    if(num[count] < num[count+1]) {
                        break;
                    } else {
                        return false;
                    }
                case '>':
                    if(num[count] > num[count+1]) {
                        break;
                    } else {
                        return false;
                    }
            }
            count++;
        }

        return true;
    }



    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}