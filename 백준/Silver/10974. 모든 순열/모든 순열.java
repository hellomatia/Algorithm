import java.io.*;

public class Main {
    int N;
    int[] tempArr;
    boolean[] isVisit;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        isVisit = new boolean[N+1];
        tempArr = new int[N];

        printNumArrAll(0);

    }

    public void printNumArrAll(int count) {
        if(count == N) {
            for(int i=0; i<N; i++) {
                System.out.print(tempArr[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i=1; i<=N; i++){
            if(!isVisit[i]) {
                isVisit[i] = true;
                tempArr[count] = i;
                printNumArrAll(count+1);
                isVisit[i] = false;
            }
        }
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}