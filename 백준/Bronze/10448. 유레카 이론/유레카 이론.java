import java.io.*;
import java.util.ArrayList;

public class Main {
    int[] num;
    ArrayList<Integer> triangularNum = new ArrayList<>();
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(bf.readLine());
        num = new int[testCase];

        int maxNum = 0;


        for(int i=0; i<testCase; i++) {
            num[i] = Integer.parseInt(bf.readLine());
            maxNum = Math.max(maxNum, num[i]);
        }

        triangularNum.add(1);

        int index = 0;
        while(triangularNum.get(index)<maxNum) {
            index++;
            triangularNum.add((index+1)*(index+2)/2);
        }

        for(int i=0; i<testCase; i++) {
            if(isEureka(i)) {
                bw.write("1\n");
            } else {
                bw.write("0\n");
            }
        }

        bw.flush();
        bw.close();
    }

    public boolean isEureka(int idx) {
        for(int i=0; i<triangularNum.size(); i++) {
            for(int j=0; j<triangularNum.size(); j++) {
                for(int k=0; k<triangularNum.size(); k++) {
                    int sum = triangularNum.get(i) + triangularNum.get(j) + triangularNum.get(k);
                    if(sum == num[idx]) return true;
                }
            }
        }
        return false;

    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}